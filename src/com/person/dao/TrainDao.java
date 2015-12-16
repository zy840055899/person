package com.person.dao;

import java.util.List;

import com.person.annotation.MyRepository;
import com.person.entity.Train;
import com.person.entity.page.TrainPage;

@MyRepository
public interface TrainDao
{
	/**��ѯȫ����ѵ*/
	List<Train> findAllTrain(TrainPage trainPage);
	/**������ж���������*/
	int findAllTrainRows();
	/**������ѵ�Ų�����ѵ*/
	Train findTrainById(int trainId);
	/**������ѵ��ɾ����ѵ*/
	int	delTrainById(int trainId);
	/**������ѵ*/
	int addTrain(Train train);
	/**�޸���ѵ*/
	int modifyTrain(Train train);
}
