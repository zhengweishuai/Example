package com.base.listener;

/**
 * 很多Fragment都需要回调此方法，所以移到base库中
 */
public interface BaseListener {

	public void onCallBack(Object object);

}
