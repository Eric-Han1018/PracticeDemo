package com.erik.test;


import javax.xml.ws.RequestWrapper;
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
     * 清空特定客户特定商品的剩余上架数
     * @param skuCode 商品码
     * @param userID  客户id
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteRest(String skuCode, String userID){
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
     * 按客户类别清空某一商品的剩余上架数
     * @param skuCode 商品码
     * @param type  客户类别
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteByType(String skuCode, String type){
        List<SdSkuSellConfigDetailEntry> sdSkuSellConfigDetailEntries = skuSellConfigDetailEntryMapper.selectByExample(new SdSkuSellConfigDetailEntryExample()
                .createCriteria()
                .andSkuCodeEqualTo(skuCode)
                .example());
        if (!CollectionUtil.isEmpty(sdSkuSellConfigDetailEntries)) {
            sdSkuSellConfigDetailEntries.forEach(entry -> {
                String userType = this.transferUserInfo(entry.getUserId()).getType();   // 获取客户类别
                if (userType.equals(type)) {
                    entry.setNum(0);
                    skuSellConfigDetailEntryMapper.updateByPrimaryKeySelective(entry);
                }
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
     * 按门店类型(除某一特定id外)清空某一商品的剩余上架数，无特例默认传“ ”
     * @param skuCode 商品码
     * @param type  客户类别
     * @param id    客户id
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteByType(String skuCode, String type, String id){
        List<SdSkuSellConfigDetailEntry> sdSkuSellConfigDetailEntries = skuSellConfigDetailEntryMapper.selectByExample(new SdSkuSellConfigDetailEntryExample()
                .createCriteria()
                .andSkuCodeEqualTo(skuCode)
                .example());
        if (!CollectionUtil.isEmpty(sdSkuSellConfigDetailEntries)) {
            sdSkuSellConfigDetailEntries.forEach(entry -> {
                String userType = this.transferUserInfo(entry.getUserId()).getType();   // 获取客户类型
                if (userType.equals(type) && !entry.getUserId().equals(id)) {           // 特定客户除外
                    entry.setNum(0);
                    skuSellConfigDetailEntryMapper.updateByPrimaryKeySelective(entry);
                }
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
        if(!CollectionUtils.isEmpty(sdSkuSellConfigDetailEntries)){
            sdSkuSellConfigDetailEntries.forEach((entry)->{
                entry.setNum(0);
                skuSellConfigDetailEntryMapper.updateByPrimaryKeySelective(entry);
            });
            List<SdSkuSellConfigEntry> sdSkuSellConfigEntries = skuSellConfigEntryMapper.selectByExample(new SdSkuSellConfigEntryExample()
                    .createCriteria()
                    .andSkuCodeEqualTo(skuCode)
                    .example());
            SdSkuSellConfigEntry sdSkuSellConfigEntry = sdSkuSellConfigEntries.get(0);
            sdSkuSellConfigEntry.setAllSellNum(0);
            skuSellConfigEntryMapper.updateByPrimaryKeySelective(sdSkuSellConfigEntry);
        }
    }
}

