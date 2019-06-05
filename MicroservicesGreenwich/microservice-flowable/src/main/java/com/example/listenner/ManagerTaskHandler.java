package com.example.listenner;

import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;

/**
 * 〈一句话功能简述〉<br>
 * 〈${DESCRIPTION}〉
 *
 * @author zhaokejin
 * @create 2019/3/1
 */

public class ManagerTaskHandler implements TaskListener {

	@Override
	public void notify(DelegateTask delegateTask) {
		delegateTask.setAssignee("经理");
	}

}