package com.person.dao;

import java.util.List;

import com.person.annotation.MyRepository;
import com.person.entity.Train;
import com.person.entity.page.TrainPage;

@MyRepository
public interface TrainDao
{
	/**查询全部培训*/
	List<Train> findAllTrain(TrainPage trainPage);
	/**查出共有多少条数据*/
	int findAllTrainRows();
	/**按照培训号查找培训*/
	Train findTrainById(int trainId);
	/**根据培训号删除培训*/
	int	delTrainById(int trainId);
	/**增加培训*/
	int addTrain(Train train);
	/**修改培训*/
	int modifyTrain(Train train);
}
