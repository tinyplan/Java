package util;

import java.util.Vector;

public class PageUtil {
	private int pageNo;//��ǰҳ��
	private int pageSize;//ÿҳ������ʾ����
	private int totalCount;//�ܼ�¼����
	private Vector<String[]> data;//������ݴ洢
	private int totalPage;//��ҳ��
	
	public PageUtil(int pageSize, int totalCount) {
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        if (this.totalCount % this.pageSize == 0) {
            this.totalPage = this.totalCount / this.pageSize;
        } else {
            this.totalPage = this.totalCount / this.pageSize + 1;
        }
    }
	
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public Vector<String[]> getData() {
		return data;
	}
	public void setData(Vector<String[]> data) {
		this.data = data;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
}
