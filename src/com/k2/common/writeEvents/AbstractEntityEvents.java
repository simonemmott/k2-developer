package com.k2.common.writeEvents;

public abstract class AbstractEntityEvents implements EntityEvents {

	@Override
	public void onNew() {}

	@Override
	public void onInsertPreValidate() {}

	@Override
	public void onInsertPostValidate() {}

	@Override
	public void onUpdatePreValidate() {}

	@Override
	public void onUpdatePostValidate() {}

	@Override
	public void onDeletePreValidate() {}

	@Override
	public void onDeletePostValidate() {}

	@Override
	public void validate() throws ValidationException {}
	
/*
    @Override
    public void onNew() { System.out.println("New. "+EntityUtil.getEntityIdentity(this)); }
    @Override
    public void onInsertPreValidate() { System.out.println("Insert pre validate. "+EntityUtil.getEntityIdentity(this)); }
    @Override
    public void onInsertPostValidate() { System.out.println("Insert post validate. "+EntityUtil.getEntityIdentity(this)); }
    @Override
    public void onUpdatePreValidate() { System.out.println("Update pre validate. "+EntityUtil.getEntityIdentity(this)); }
    @Override
    public void onUpdatePostValidate() { System.out.println("Update post validate. "+EntityUtil.getEntityIdentity(this)); }
    @Override
    public void onDeletePreValidate() { System.out.println("Delete pre validate. "+EntityUtil.getEntityIdentity(this)); }
    @Override
    public void onDeletePostValidate() { System.out.println("Delete post validate. "+EntityUtil.getEntityIdentity(this)); }
    @Override
	public void validate() throws ValidationException {
		if (<<validate condition failed>>) { throw new ValidationException("<<validation failure message>>"); } 
	}
*/


}
