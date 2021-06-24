package com.erik.test;


import javax.xml.ws.RequestWrapper;
import java.util.ArrayList;
import java.util.List;


public class Yooz01 {
//    public void demo(){
//
//        SdSkuSellConfigDetailEntryExample example = new SdSkuSellConfigDetailEntryExample();
//        example.createCriteria().andSkuCodeEqualTo(skucode).andUserIdEqualTo(userid);
//        List<SdSkuSellConfigDetailEntry> sdSkuSellConfigDetailEntries = skuSellConfigDetailEntryMapper.selectByExample(example);
//        if(!CollectionUtils.isEmpty(sdSkuSellConfigDetailEntries)) {
//            sdSkuSellConfigDetailEntries.get(0).setNum(0);
//        }else {
//            //error
//        }
//        skuSellConfigDetailEntryMapper.updateByPrimaryKey(sdSkuSellConfigDetailEntries.get(0));
//        Long sumAllNum = skuSellConfigDetailEntryMapper.sumAllNum(skucode);
//
//    }
    @SuppressWarnings("all")
    @GetMapping("/deleteRest")
    public void deleteRest(@RequestParam("skuCode") String skuCode, @RequestParam("userId") String userId) {
        skuSellConfigService.deleteRest(skuCode, userId);
    }

    /**
     * 清空某一客户某一商品的剩余上架数
     * @param skuCode 商品码
     * @param userID  客户id
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteDetailedIdAndSku(String skuCode, String userID){
        List<SdSkuSellConfigDetailEntry> sdSkuSellConfigDetailEntries = skuSellConfigDetailEntryMapper.selectByExample(new SdSkuSellConfigDetailEntryExample()
                .createCriteria()
                .andSkuCodeEqualTo(skuCode)
                .andUserIdEqualTo(userID)
                .example());
        if(!CollectionUtils.isEmpty(sdSkuSellConfigDetailEntries)) {
            //剩余上架数清零
            sdSkuSellConfigDetailEntries.get(0).setNum(0);
            skuSellConfigDetailEntryMapper.updateByPrimaryKeySelective(sdSkuSellConfigDetailEntries.get(0));
            //update上架总量
            List<SdSkuSellConfigEntry> sdSkuSellConfigEntries = skuSellConfigEntryMapper.selectByExample(new SdSkuSellConfigEntryExample()
                    .createCriteria()
                    .andSkuCodeEqualTo(skuCode)
                    .example());
            SdSkuSellConfigEntry sdSkuSellConfigEntry = sdSkuSellConfigEntries.get(0);
            Long sumAllNum = skuSellConfigDetailEntryMapper.sumAllNum(skuCode);
            sdSkuSellConfigEntry.setAllSellNum(sumAllNum.intValue());
            skuSellConfigEntryMapper.updateByPrimaryKeySelective(sdSkuSellConfigEntry);
        }
    }

    /**
     * 清空某一客户类型的所有商品
     * @param type 客户类型
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteAllbyType(String type){
        List<String> userId = skuSellConfigDetailEntryMapper.selectClientByType(type);
        if(!CollectionUtils.isEmpty(userId)){
            List<SdSkuSellConfigDetailEntry> sdSkuSellConfigDetailEntries = skuSellConfigDetailEntryMapper.selectByExample(new SdSkuSellConfigDetailEntryExample()
                    .createCriteria()
                    .andUserIdIn(userId)
                    .example());
            sdSkuSellConfigDetailEntries.forEach(entry->{
                entry.setNum(0);
                skuSellConfigDetailEntryMapper.updateByPrimaryKeySelective(entry);
            });
            List<SdSkuSellConfigEntry> sdSkuSellConfigEntries = skuSellConfigEntryMapper.selectByExample(new SdSkuSellConfigEntryExample()
                    .createCriteria()
                    .andAllSellNumGreaterThan(0)
                    .example());
            sdSkuSellConfigEntries.forEach(entry->{
                Long sumAllNum = skuSellConfigDetailEntryMapper.sumAllNum(entry.getSkuCode());
                entry.setAllSellNum(sumAllNum.intValue());
                skuSellConfigEntryMapper.updateByPrimaryKeySelective(entry);
            });
        }

    }

    /**
     * 按门店类型(除某一特定id外)清空某一商品的剩余上架数，无特例默认传“ ”
     * @param skuCode 商品码
     * @param type  客户类别
     * @param id    不需要清空的客户id
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteByType(String skuCode, String type, List<String> id){
        List<String> userId = skuSellConfigDetailEntryMapper.selectClientByType(type);  // 该客户类型的下的所有userId
        List<SdSkuSellConfigDetailEntry> sdSkuSellConfigDetailEntries = skuSellConfigDetailEntryMapper.selectByExample(new SdSkuSellConfigDetailEntryExample()
                .createCriteria()
                .andSkuCodeEqualTo(skuCode)
                .andUserIdIn(userId)
                .andUserIdNotIn(id)
                .example());
        if (!CollectionUtil.isEmpty(sdSkuSellConfigDetailEntries)) {
            sdSkuSellConfigDetailEntries.forEach(entry -> {
                entry.setNum(0);
                skuSellConfigDetailEntryMapper.updateByPrimaryKeySelective(entry);
            });
            List<SdSkuSellConfigEntry> sdSkuSellConfigEntries = skuSellConfigEntryMapper.selectByExample(new SdSkuSellConfigEntryExample()
                    .createCriteria()
                    .andSkuCodeEqualTo(skuCode)
                    .example());
            Long sumAllNum = skuSellConfigDetailEntryMapper.sumAllNum(skuCode);
            sdSkuSellConfigEntries.get(0).setAllSellNum(sumAllNum.intValue());
            skuSellConfigEntryMapper.updateByPrimaryKeySelective(sdSkuSellConfigEntries.get(0));
        }
    }

    /**
     *  清空单个商品所有剩余上架数
     * @param skuCode   商品sku码
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteBySkuCode(String skuCode){
        List<SdSkuSellConfigDetailEntry> sdSkuSellConfigDetailEntries = skuSellConfigDetailEntryMapper.selectByExample(new SdSkuSellConfigDetailEntryExample()
                .createCriteria()
                .andSkuCodeEqualTo(skuCode)
                .example());
        List<SdSkuSellConfigEntry> sdSkuSellConfigEntries = skuSellConfigEntryMapper.selectByExample(new SdSkuSellConfigEntryExample()
                .createCriteria()
                .andSkuCodeEqualTo(skuCode)
                .example());
        if(!CollectionUtils.isEmpty(sdSkuSellConfigDetailEntries)){
            SdSkuSellConfigEntry sdSkuSellConfigEntry = sdSkuSellConfigEntries.get(0);
            if(sdSkuSellConfigEntry.getSellType() == 2) {
                sdSkuSellConfigDetailEntries.forEach((entry) -> {
                    entry.setNum(0);
                    skuSellConfigDetailEntryMapper.updateByPrimaryKeySelective(entry);
                });
            }
            sdSkuSellConfigEntry.setAllSellNum(0);
            skuSellConfigEntryMapper.updateByPrimaryKeySelective(sdSkuSellConfigEntry);
        }
    }

    /**
     *  清空剩余上架数
     * @param skuCode 需要清空的商品码
     * @param savedUserId   需要保留的客户id
     * @param types 需要清空的客户类型
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteTheRest(List<String> skuCode, List<String> savedUserId, List<String> types){
        List<String> deletedUserId = new ArrayList<>();
        types.forEach(type->{deletedUserId.addAll(skuSellConfigDetailEntryMapper.selectClientByType(type));});
        List<SdSkuSellConfigDetailEntry> sdSkuSellConfigDetailEntries = skuSellConfigDetailEntryMapper.selectByExample(new SdSkuSellConfigDetailEntryExample()
                .createCriteria()
                .andSkuCodeIn(skuCode)
                .andUserIdIn(deletedUserId)
                .andUserIdNotIn(savedUserId)
                .example());
        if(!CollectionUtils.isEmpty(sdSkuSellConfigDetailEntries)){
            sdSkuSellConfigDetailEntries.forEach(entry -> {
                entry.setNum(0);
                skuSellConfigDetailEntryMapper.updateByPrimaryKeySelective(entry);
            });
            List<SdSkuSellConfigEntry> sdSkuSellConfigEntries = skuSellConfigEntryMapper.selectByExample(new SdSkuSellConfigEntryExample()
                    .createCriteria()
                    .andSkuCodeIn(skuCode)
                    .example());
            sdSkuSellConfigEntries.forEach(entry ->{
                Long sumAllNum = skuSellConfigDetailEntryMapper.sumAllNum(entry.getSkuCode());
                entry.setAllSellNum(sumAllNum.intValue());
                skuSellConfigEntryMapper.updateByPrimaryKeySelective(entry);
            });
        }
    }
}

