package tw.com.finalproj.dao;

import java.util.List;

import tw.com.finalproj.service.domain.DietBean;
import tw.com.finalproj.service.domain.DietDetailsBean;

public interface DietDetailsDAO {
	public abstract DietBean select(Integer dietdetailid);

	public abstract List <DietDetailsBean>  select();
	
	public abstract DietDetailsBean insert(DietDetailsBean bean);
	
	public abstract DietDetailsBean delete(Integer dietdetailid);
	
	public abstract DietDetailsBean updata(Integer dietdetailid,Integer dietid,Integer sampleid,Integer foodid,Integer foodnumber);
}