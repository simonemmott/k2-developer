package com.k2.common.writeEvents;

public interface EntityEvents {
	
	public void onNew();
	public void onInsertPreValidate();
	public void onInsertPostValidate();
	public void onUpdatePreValidate();
	public void onUpdatePostValidate();
	public void onDeletePreValidate();
	public void onDeletePostValidate();
	public void validate() throws ValidationException;

}
