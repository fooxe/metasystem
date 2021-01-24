package com.dashow.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.asiainfo.bamboo.CommonContext;
import com.asiainfo.crm.order.common.ErrResultCode;
import com.asiainfo.crm.order.common.OrderMDA;
import com.asiainfo.crm.order.common.utils.ListUtil;
import com.asiainfo.crm.order.multi.outer.ISRCardOuter;
import com.asiainfo.crm.order.multi.outer.ISRInstOuter;
import com.asiainfo.crm.order.multi.outer.ISRPnOuter;
import com.asiainfo.crm.order.vo.sr.CardInstBatchReleaseVo;
import com.asiainfo.crm.order.vo.sr.InstUseBackVo;
import com.asiainfo.crm.order.vo.sr.NumInstBatchReleaseVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

@Service
public class SrServiceExecuteTask {

    @Autowired
    ISRCardOuter cardOuter;
    @Autowired
    ISRInstOuter instOuter;
    @Autowired
    ISRPnOuter pnOuter;


    @Async
    public Future<Map<String, Object>> call(Integer type, List list, String regionId, String channelNbr, String staffCode, String custOrderId,String logId) {
        Map<String, Object> returnMap=new HashMap<>();
        try {
            CommonContext.INST.putHead(OrderMDA.HTTP_REQUEST_GLOBAL_LOG_ID,logId);
            LogTaskFactory.setKeyWord(custOrderId);
            List<Map<String, Object>> retList = null;
            switch (type) {
                case 1:
                    //号码出库
                    try {
                        pnOuter.numInstBatchUse(regionId, channelNbr, staffCode, list);
                        returnMap.put(OrderMDA.RESULTCODE, OrderMDA.ZERO);
                    } catch (Exception e) {
                        returnMap.put(OrderMDA.RESULTCODE, OrderMDA.NEGATIVEONE);
                        returnMap.put(OrderMDA.RESULTMSG, e.getMessage());
                    } finally {
                        returnMap.put("revertList", 1);
                        returnMap.put(OrderMDA.REVERT, "1");
                    }
                    break;
                case 2:
                    //卡出库
                    try {
                        cardOuter.useMktResCardInstBatch(regionId, channelNbr, staffCode, list);
                        returnMap.put(OrderMDA.RESULTCODE, OrderMDA.ZERO);
                    } catch (Exception e) {
                        returnMap.put(OrderMDA.RESULTCODE, OrderMDA.NEGATIVEONE);
                        returnMap.put(OrderMDA.RESULTMSG, e.getMessage());
                    } finally {
                        returnMap.put("revertList", 2);
                        returnMap.put(OrderMDA.REVERT, "1");
                    }
                    break;
                case 3:
                    //终端出库
                    try {
                        retList = (List<Map<String, Object>>) instOuter.useMktResInst(regionId, channelNbr, list);
                        List<InstUseBackVo> backlist = ListUtil.newArrayList();
                        for (Map<String, Object> map : ListUtil.nvlList(retList)) {
                            InstUseBackVo vo = JSON.parseObject(JSON.toJSONString(map), InstUseBackVo.class);
                            backlist.add(vo);
                        }
                        returnMap.put(OrderMDA.SALENOLIST, backlist);
                        returnMap.put(OrderMDA.RESULTCODE, OrderMDA.ZERO);
                    } catch (Exception e) {
                        returnMap.put(OrderMDA.RESULTCODE, OrderMDA.NEGATIVEONE);
                        returnMap.put(OrderMDA.RESULTMSG, e.getMessage());
                    } finally {
                        returnMap.put("revertList", 3);
                        returnMap.put(OrderMDA.REVERT, "1");
                    }
                    break;
                case 4:
                    //号码出库回退
                    try {
                        pnOuter.numInstBatchresetting(regionId, channelNbr, staffCode, list);
                        returnMap.put(OrderMDA.RESULTCODE, OrderMDA.ZERO);
                    } catch (Exception e) {
                    }
                    break;
                case 5:
                    //卡出库回退
                    try {
                        cardOuter.useMktResCardInstBackBatch(regionId, channelNbr, staffCode, list);
                        returnMap.put(OrderMDA.RESULTCODE, OrderMDA.ZERO);
                    } catch (Exception e) {
                    }
                    break;
                case 6:
                    //终端出库回退
                    try {
                        retList = (List<Map<String, Object>>) instOuter.useMktResInstRevert(regionId, channelNbr, staffCode, list);
                        List<InstUseBackVo> backlist = ListUtil.newArrayList();
                        for (Map<String, Object> map : ListUtil.nvlList(retList)) {
                            InstUseBackVo vo = JSON.parseObject(JSON.toJSONString(map), InstUseBackVo.class);
                            backlist.add(vo);
                        }
                        returnMap.put(OrderMDA.SALENOLIST, backlist);
                        returnMap.put(OrderMDA.RESULTCODE, OrderMDA.ZERO);
                    } catch (Exception e) {
                    }
                    break;
                case 7:
                    //号码拆机
                    try {
                        pnOuter.numInstBatchDelete(regionId, channelNbr, staffCode, list);
                        returnMap.put(OrderMDA.RESULTCODE, OrderMDA.ZERO);
                    } catch (Exception e) {
                    }
                    break;
                case 8:
                    //号码入库
                    try {
                        pnOuter.pnInputStore(regionId, channelNbr, staffCode, list);
                        returnMap.put(OrderMDA.RESULTCODE, OrderMDA.ZERO);
                    } catch (Exception e) {
                    }
                    break;
                case 9:
                    //卡拆机
                    try {
                        cardOuter.disassembleMktResCardInstBatch(regionId, channelNbr, staffCode, list);
                        returnMap.put(OrderMDA.RESULTCODE, OrderMDA.ZERO);
                    } catch (Exception e) {
                    }
                    break;
                case 10:
                    //退换货&退货
                    try {
                        instOuter.changeMktResInst(regionId, channelNbr, staffCode, list);
                        returnMap.put(OrderMDA.RESULTCODE, OrderMDA.ZERO);
                    } catch (Exception e) {
                    }
                    break;
                case 11:
                    //批量卡释放
                    CardInstBatchReleaseVo releaseVo = new CardInstBatchReleaseVo();
                    releaseVo.setInstNbrs(list);
                    try {
                        cardOuter.releaseMktResCardInstBatch(regionId, channelNbr, staffCode, releaseVo);
                        returnMap.put(OrderMDA.RESULTCODE, OrderMDA.ZERO);
                    } catch (Exception e) {
                    }
                    break;
                case 12:
                    //批量号码释放
                    NumInstBatchReleaseVo releaseNum = new NumInstBatchReleaseVo();
                    releaseNum.setPhoneNums(list);
                    try {
                        pnOuter.occupiedNumInstBatchRelease(regionId, channelNbr, staffCode, releaseNum);
                        returnMap.put(OrderMDA.RESULTCODE, OrderMDA.ZERO);
                    } catch (Exception e) {
                    }
                    break;
            }
        } catch (Exception e) {
            returnMap.put(OrderMDA.RESULTCODE, OrderMDA.NEGATIVEONE);
            returnMap.put(OrderMDA.RESULTMSG, e.getMessage());
        } finally {
            if (returnMap.get(OrderMDA.RESULTCODE) != null && OrderMDA.NEGATIVEONE.equals(returnMap.get(OrderMDA.RESULTCODE))) {
                if (returnMap.get(OrderMDA.RESULTMSG) != null && returnMap.get(OrderMDA.RESULTMSG).toString().startsWith("{")) {
                    JSONObject resp = JSONObject.parseObject(returnMap.get(OrderMDA.RESULTMSG).toString());
                    if (StringUtils.isNotBlank(resp.getString(OrderMDA.CODE))) {
                        returnMap.put(OrderMDA.CODE, resp.getString(OrderMDA.CODE));
                    } else {
                        if ("1".equals(returnMap.get(OrderMDA.REVERT))) {
                            returnMap.put(OrderMDA.CODE, ErrResultCode.SR_PN_COMMON_ERROR);
                        } else if ("2".equals(returnMap.get(OrderMDA.REVERT))) {
                            returnMap.put(OrderMDA.CODE, ErrResultCode.SR_CARD_COMMON_ERROR);
                        } else if ("3".equals(returnMap.get(OrderMDA.REVERT))) {
                            returnMap.put(OrderMDA.CODE, ErrResultCode.SR_INST_COMMON_ERROR);
                        }
                    }
                    returnMap.put(OrderMDA.REASON, resp.getString(OrderMDA.REASON));
                }
            }
        }
        return new AsyncResult<>(returnMap);
    }
}