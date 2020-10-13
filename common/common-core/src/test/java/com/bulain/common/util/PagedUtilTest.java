package com.bulain.common.util;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.util.CollectionUtils;

public class PagedUtilTest {
	
    @Test
	@SuppressWarnings("unchecked")
	public void testGetPage1() {
		List<Integer> list = new ArrayList<Integer>();
		
		list.add(1);
		assertEquals(list,PagedUtil.getPageList(1,1));
		
		list.add(2);
		assertEquals(list,PagedUtil.getPageList(1,2));
		
		list.add(3);
		assertEquals(list,PagedUtil.getPageList(1,3));
		
		list.add(4);
		assertEquals(list,PagedUtil.getPageList(1,4));
		
		list.add(5);
		assertEquals(list,PagedUtil.getPageList(1,5));
		
		list.add(6);
		assertEquals(list,PagedUtil.getPageList(1,6));
		
		list.add(7);
		assertEquals(list,PagedUtil.getPageList(1,7));
		
		list.add(8);
		assertEquals(list,PagedUtil.getPageList(1,8));
		
		list.add(9);
		assertEquals(list,PagedUtil.getPageList(1,9));
		
		list.add(10);
		assertEquals(list,PagedUtil.getPageList(1,10));
		
		list = CollectionUtils.arrayToList(new Integer[]{1,2,3,4,5,6,7,8,9,10,11});
		assertEquals(list,PagedUtil.getPageList(1,11));
		
		list = CollectionUtils.arrayToList(new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12});
		assertEquals(list,PagedUtil.getPageList(1,12));
		
		list = CollectionUtils.arrayToList(new Integer[]{1,2,3,4,5,6,7,8,9,-1,12,13});
		assertEquals(list,PagedUtil.getPageList(1,13));
		
		list = CollectionUtils.arrayToList(new Integer[]{1,2,3,4,5,6,7,8,9,-1,13,14});
		assertEquals(list,PagedUtil.getPageList(1,14));
		
		list = CollectionUtils.arrayToList(new Integer[]{1,2,3,4,5,6,7,8,9,-1,14,15});
		assertEquals(list,PagedUtil.getPageList(1,15));
		
		list = CollectionUtils.arrayToList(new Integer[]{1,2,3,4,5,6,7,8,9,-1,99,100});
		assertEquals(list,PagedUtil.getPageList(1,100));
	}
	
    @Test
	@SuppressWarnings("rawtypes")
    public void testGetPage2() {
		List list = CollectionUtils.arrayToList(new Integer[]{1,2});
		assertEquals(list,PagedUtil.getPageList(2,2));
		
		list = CollectionUtils.arrayToList(new Integer[]{1,2,3});
		assertEquals(list,PagedUtil.getPageList(2,3));
		
		list = CollectionUtils.arrayToList(new Integer[]{1,2,3,4});
		assertEquals(list,PagedUtil.getPageList(2,4));
		
		list = CollectionUtils.arrayToList(new Integer[]{1,2,3,4,5});
		assertEquals(list,PagedUtil.getPageList(2,5));
		
		list = CollectionUtils.arrayToList(new Integer[]{1,2,3,4,5,6});
		assertEquals(list,PagedUtil.getPageList(2,6));
		
		list = CollectionUtils.arrayToList(new Integer[]{1,2,3,4,5,6,7});
		assertEquals(list,PagedUtil.getPageList(2,7));
		
		list = CollectionUtils.arrayToList(new Integer[]{1,2,3,4,5,6,7,8});
		assertEquals(list,PagedUtil.getPageList(2,8));
		
		list = CollectionUtils.arrayToList(new Integer[]{1,2,3,4,5,6,7,8,9});
		assertEquals(list,PagedUtil.getPageList(2,9));
		
		list = CollectionUtils.arrayToList(new Integer[]{1,2,3,4,5,6,7,8,9,10});
		assertEquals(list,PagedUtil.getPageList(2,10));
		
		list = CollectionUtils.arrayToList(new Integer[]{1,2,3,4,5,6,7,8,9,10,11});
		assertEquals(list,PagedUtil.getPageList(2,11));
		
		list = CollectionUtils.arrayToList(new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12});
		assertEquals(list,PagedUtil.getPageList(2,12));
		
		list = CollectionUtils.arrayToList(new Integer[]{1,2,3,4,5,6,7,8,9,-1,12,13});
		assertEquals(list,PagedUtil.getPageList(2,13));
		
		list = CollectionUtils.arrayToList(new Integer[]{1,2,3,4,5,6,7,8,9,-1,13,14});
		assertEquals(list,PagedUtil.getPageList(2,14));
		
		list = CollectionUtils.arrayToList(new Integer[]{1,2,3,4,5,6,7,8,9,-1,14,15});
		assertEquals(list,PagedUtil.getPageList(2,15));
		
		list = CollectionUtils.arrayToList(new Integer[]{1,2,3,4,5,6,7,8,9,-1,99,100});
		assertEquals(list,PagedUtil.getPageList(2,100));
	}
	
    @Test
	@SuppressWarnings("rawtypes")
	public void testGetPage5() {
        List list = CollectionUtils.arrayToList(new Integer[]{1,2,3,4,5});
		assertEquals(list,PagedUtil.getPageList(5,5));
		
		list = CollectionUtils.arrayToList(new Integer[]{1,2,3,4,5,6});
		assertEquals(list,PagedUtil.getPageList(5,6));
		
		list = CollectionUtils.arrayToList(new Integer[]{1,2,3,4,5,6,7});
		assertEquals(list,PagedUtil.getPageList(5,7));
		
		list = CollectionUtils.arrayToList(new Integer[]{1,2,3,4,5,6,7,8});
		assertEquals(list,PagedUtil.getPageList(5,8));
		
		list = CollectionUtils.arrayToList(new Integer[]{1,2,3,4,5,6,7,8,9});
		assertEquals(list,PagedUtil.getPageList(5,9));
		
		list = CollectionUtils.arrayToList(new Integer[]{1,2,3,4,5,6,7,8,9,10});
		assertEquals(list,PagedUtil.getPageList(5,10));
		
		list = CollectionUtils.arrayToList(new Integer[]{1,2,3,4,5,6,7,8,9,10,11});
		assertEquals(list,PagedUtil.getPageList(5,11));
		
		list = CollectionUtils.arrayToList(new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12});
		assertEquals(list,PagedUtil.getPageList(5,12));
		
		list = CollectionUtils.arrayToList(new Integer[]{1,2,3,4,5,6,7,8,9,-1,12,13});
		assertEquals(list,PagedUtil.getPageList(5,13));
		
		list = CollectionUtils.arrayToList(new Integer[]{1,2,3,4,5,6,7,8,9,-1,13,14});
		assertEquals(list,PagedUtil.getPageList(5,14));
		
		list = CollectionUtils.arrayToList(new Integer[]{1,2,3,4,5,6,7,8,9,-1,14,15});
		assertEquals(list,PagedUtil.getPageList(5,15));
		
		list = CollectionUtils.arrayToList(new Integer[]{1,2,3,4,5,6,7,8,9,-1,99,100});
		assertEquals(list,PagedUtil.getPageList(5,100));
	}
	
    @Test
	@SuppressWarnings("rawtypes")
	public void testGetPage9() {
		List list = CollectionUtils.arrayToList(new Integer[]{1,2,3,4,5,6,7,8,9});
		assertEquals(list,PagedUtil.getPageList(9,9));
		
		list = CollectionUtils.arrayToList(new Integer[]{1,2,3,4,5,6,7,8,9,10});
		assertEquals(list,PagedUtil.getPageList(9,10));
		
		list = CollectionUtils.arrayToList(new Integer[]{1,2,3,4,5,6,7,8,9,10,11});
		assertEquals(list,PagedUtil.getPageList(9,11));
		
		list = CollectionUtils.arrayToList(new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12});
		assertEquals(list,PagedUtil.getPageList(9,12));
		
		list = CollectionUtils.arrayToList(new Integer[]{1,2,-1,5,6,7,8,9,10,11,12,13});
		assertEquals(list,PagedUtil.getPageList(9,13));
		
		list = CollectionUtils.arrayToList(new Integer[]{1,2,-1,5,6,7,8,9,10,11,12,13,14});
		assertEquals(list,PagedUtil.getPageList(9,14));
		
		list = CollectionUtils.arrayToList(new Integer[]{1,2,-1,5,6,7,8,9,10,11,12,13,14,15});
		assertEquals(list,PagedUtil.getPageList(9,15));
		
		list = CollectionUtils.arrayToList(new Integer[]{1,2,-1,5,6,7,8,9,10,11,12,13,14,15,16});
		assertEquals(list,PagedUtil.getPageList(9,16));
		
		list = CollectionUtils.arrayToList(new Integer[]{1,2,-1,5,6,7,8,9,10,11,12,13,-1,16,17});
		assertEquals(list,PagedUtil.getPageList(9,17));
		
		list = CollectionUtils.arrayToList(new Integer[]{1,2,-1,5,6,7,8,9,10,11,12,13,-1,99,100});
		assertEquals(list,PagedUtil.getPageList(9,100));
	}
	
    @Test
	@SuppressWarnings("rawtypes")
	public void testGetPage10() {
		List list = CollectionUtils.arrayToList(new Integer[]{1,2,3,4,5,6,7,8,9,10});
		assertEquals(list,PagedUtil.getPageList(10,10));
		
		list = CollectionUtils.arrayToList(new Integer[]{1,2,3,4,5,6,7,8,9,10,11});
		assertEquals(list,PagedUtil.getPageList(10,11));
		
		list = CollectionUtils.arrayToList(new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12});
		assertEquals(list,PagedUtil.getPageList(10,12));
		
		list = CollectionUtils.arrayToList(new Integer[]{1,2,-1,5,6,7,8,9,10,11,12,13});
		assertEquals(list,PagedUtil.getPageList(10,13));
		
		list = CollectionUtils.arrayToList(new Integer[]{1,2,-1,6,7,8,9,10,11,12,13,14});
		assertEquals(list,PagedUtil.getPageList(10,14));
		
		list = CollectionUtils.arrayToList(new Integer[]{1,2,-1,6,7,8,9,10,11,12,13,14,15});
		assertEquals(list,PagedUtil.getPageList(10,15));
		
		list = CollectionUtils.arrayToList(new Integer[]{1,2,-1,6,7,8,9,10,11,12,13,14,15,16});
		assertEquals(list,PagedUtil.getPageList(10,16));
		
		list = CollectionUtils.arrayToList(new Integer[]{1,2,-1,6,7,8,9,10,11,12,13,14,15,16,17});
		assertEquals(list,PagedUtil.getPageList(10,17));
		
		list = CollectionUtils.arrayToList(new Integer[]{1,2,-1,6,7,8,9,10,11,12,13,14,-1,17,18});
		assertEquals(list,PagedUtil.getPageList(10,18));
		
		list = CollectionUtils.arrayToList(new Integer[]{1,2,-1,6,7,8,9,10,11,12,13,14,-1,99,100});
		assertEquals(list,PagedUtil.getPageList(10,100));
	}

}
