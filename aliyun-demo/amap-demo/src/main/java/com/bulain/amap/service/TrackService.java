package com.bulain.amap.service;

import java.util.List;

import com.bulain.amap.pojo.PointColumnInfo;
import com.bulain.amap.pojo.PointInfo;
import com.bulain.amap.pojo.ServiceInfo;
import com.bulain.amap.pojo.TerminalColumnInfo;
import com.bulain.amap.pojo.TerminalInfo;
import com.bulain.amap.pojo.TerminalTrackInfo;
import com.bulain.amap.pojo.TerminalTrackReq;
import com.bulain.amap.pojo.TraceInfo;
import com.bulain.amap.pojo.TrackInfo;
import com.bulain.amap.pojo.TrackReq;
import com.bulain.core.pojo.BaseResp;
import com.bulain.core.pojo.DataResp;
import com.bulain.core.pojo.ListResp;
import com.bulain.core.pojo.PageReq;

/***
 * 猎鹰轨迹服务接口
 * @author Bulain
 */
public interface TrackService {

	/**增加service*/
	DataResp<ServiceInfo> serviceAdd(ServiceInfo data);
	/**删除service*/
	BaseResp serviceDelete(ServiceInfo data);
	/**修改service*/
	DataResp<ServiceInfo> serviceUpdate(ServiceInfo data);
	/**查询service*/
	ListResp<ServiceInfo> serviceList();

	/**增加终端*/
	DataResp<TerminalInfo> terminalAdd(TerminalInfo data);
	/**删除终端*/
	BaseResp terminalDelete(TerminalInfo data);
	/**修改终端*/
	DataResp<TerminalInfo> terminalUpdate(TerminalInfo data);
	/**查询终端*/
	ListResp<TerminalInfo> terminalList(TerminalInfo data, PageReq page);

	/**关键字搜索终端*/
	ListResp<TerminalTrackInfo> terminalSearch(TerminalTrackReq data, PageReq page);
	/**周边搜索终端*/
	ListResp<TerminalTrackInfo> terminalAroundsearch(TerminalTrackReq data, PageReq page);
	/**多边形内搜索终端*/
	ListResp<TerminalTrackInfo> terminalPolygonsearch(TerminalTrackReq data, PageReq page);
	/**行政区域内搜索终端*/
	ListResp<TerminalTrackInfo> terminalDistrictsearch(TerminalTrackReq data, PageReq page);

	/**增加轨迹*/
	DataResp<TraceInfo> traceAdd(TraceInfo data);
	/**删除轨迹*/
	BaseResp traceDelete(TraceInfo data);
	/**轨迹点上传*/
	ListResp<PointInfo> pointUpload(TraceInfo data, List<PointInfo> list);

	/**实时查询某终端位置*/
	ListResp<PointInfo> terminalLastpoint(TraceInfo data);
	/**查询某终端某几条轨迹信息*/
	ListResp<TrackInfo> terminalTrsearch(TrackReq data, PageReq page);

	/**终端自定义字段的增加*/
	DataResp<TerminalColumnInfo> terminalColumnAdd(TerminalColumnInfo data);
	/**终端自定义字段的删除*/
	BaseResp terminalColumnDelete(TerminalColumnInfo data);
	/**终端自定义字段的修改*/
	DataResp<TerminalColumnInfo> terminalColumnUpdate(TerminalColumnInfo data);
	/**终端自定义字段的查询*/
	ListResp<TerminalColumnInfo> terminalColumnList(TerminalColumnInfo data);

	/**轨迹自定义字段的增加*/
	DataResp<PointColumnInfo> pointColumnAdd(PointColumnInfo data);
	/**轨迹自定义字段的删除*/
	BaseResp pointColumnDelete(PointColumnInfo data);
	/**轨迹自定义字段的修改*/
	DataResp<PointColumnInfo> pointColumnUpdate(PointColumnInfo data);
	/**轨迹自定义字段的查询*/
	ListResp<PointColumnInfo> pointColumnList(PointColumnInfo data);

}
