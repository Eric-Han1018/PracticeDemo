package com.erik.test;


import javax.xml.ws.RequestWrapper;
import java.util.List;


//public class Yooz01 {
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

    @GetMapping("/deleteRest")
    public void deleteRest(@RequestParam("skuCode") String skuCode, @RequestParam("userId") String userId) {
        skuSellConfigService.deleteRest(skuCode, userId);
    }


    /**
     *  清空剩余上架数
     *
     * @return void
     * @Author
     * @Param String, String
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteRest(String skuCode, String userID){
        SdSkuSellConfigDetailEntryExample example = new SdSkuSellConfigDetailEntryExample();
        example.createCriteria().andSkuCodeEqualTo(skuCode).andUserIdEqualTo(userID);
        List<SdSkuSellConfigDetailEntry> sdSkuSellConfigDetailEntries = skuSellConfigDetailEntryMapper.selectByExample(example);
        if(!CollectionUtils.isEmpty(sdSkuSellConfigDetailEntries)) {
            //剩余上架数清零
            sdSkuSellConfigDetailEntries.get(0).setNum(0);
            skuSellConfigDetailEntryMapper.updateByPrimaryKeySelective(sdSkuSellConfigDetailEntries.get(0));
            //update上架总量
            List<SdSkuSellConfigEntry> sdSkuSellConfigEntries = skuSellConfigEntryMapper.selectByExample(new SdSkuSellConfigEntryExample()
                    .createCriteria()
                    .andIdEqualTo(sdSkuSellConfigDetailEntries.get(0).getSkuSellConfigId())
                    .example());
            SdSkuSellConfigEntry sdSkuSellConfigEntry = sdSkuSellConfigEntries.get(0);
            Long sumAllNum = skuSellConfigDetailEntryMapper.sumAllNum(skuCode);
            sdSkuSellConfigEntry.setAllSellNum(sumAllNum.intValue());
            skuSellConfigEntryMapper.updateByPrimaryKeySelective(sdSkuSellConfigEntry);
        }
    }



/**
 *  按门店类型清空剩余上架数
 * @param skuCode
 * @param type
 */

public void deleteByType(String skuCode, String type){
        SdMoneyAccountEntryExample example = new SdMoneyAccountEntryExample();
        example.createCriteria().andUserTypeEqualTo(type);
        List<SdMoneyAccountEntry> sdMoneyAccountEntries = SdMoneyAccountEntryMapper.selectByExample(example);
        if(!CollectionUtils.isEmpty(sdMoneyAccountEntries)) {
        for (SdMoneyAccountEntry i : sdMoneyAccountEntries) {
        String userId = i.getUserId();
        SdSkuSellConfigDetailEntryExample delete = new SdSkuSellConfigDetailEntryExample();
        delete.createCriteria().andSkuCodeEqualTo(skuCode).andUserIdEqualTo(userId);
        List<SdSkuSellConfigDetailEntry> sdSkuSellConfigDetailEntries = skuSellConfigDetailEntryMapper.selectByExample(delete);
        if (!CollectionUtils.isEmpty(sdSkuSellConfigDetailEntries)) {
        //剩余上架数清零
        sdSkuSellConfigDetailEntries.get(0).setNum(0);
        skuSellConfigDetailEntryMapper.updateByPrimaryKeySelective(sdSkuSellConfigDetailEntries.get(0));
        }
        }
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
}

