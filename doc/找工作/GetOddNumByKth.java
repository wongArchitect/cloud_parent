package test;

import java.util.ArrayList;
import java.util.List;

/**
 *       查找出第K大的奇数，如果没有便返回0
 * @author jodron
 * @date 2019-11-27
 */
public class GetOddNumByKth {

	/**
	 *       把数组中的数字按照升序排序，并返回升序排列的所有奇数
	 * @param numArr 数字数组
	 * @return 从数字数组取出的所有奇数，并按照升序排列
	 */
	public Object[] getSortedOddNum(int[] numArr) {
		//存放所有奇数
		List<Integer> OddNums = new ArrayList<Integer>();
		//冒泡法升序排序数组，并且取出奇数放在list中
		for (int i = 0; i < numArr.length - 1; i++) {
            for (int j = i + 1; j < numArr.length; j++) {
                if (numArr[i] > numArr[j]) {
                	//交换元素位置
                    int temp = numArr[j];
                    numArr[j] = numArr[i];
                    numArr[i] = temp;
                }
            }
            //取出除最后一个数字的奇数
            if(numArr[i] % 2 == 1) {
            	OddNums.add(numArr[i]);
            }

        }
		//判断最后一个数字是否为奇数，是则放到list中
		if(numArr[numArr.length - 1] % 2 == 1) {
			OddNums.add(numArr[numArr.length - 1]);		
		}
		//把list转为数组
		return OddNums.toArray();
	}
	
	/**
	 * 计算getSortedOddNum方法的时间复杂度
	 * @param arr 所要查找的数字数组
	 * @return 时间复杂度值
	 */
	public int timeComplexity(int[] arr) {
		//初始化时间复杂度变量
		int timeComplexity = 1;
		//计算时间复杂度
		for(int i = arr.length - 1; i >= 1; i--) {
			timeComplexity += i;
		}
		//返回时间复杂度
		return timeComplexity;
	}
	
	/**
	 * 查找数组arr中第K大的奇数 ，如果不存在则返回0
	 * @param arr 所要查找的数字数组
	 * @param k 第几个
	 * @return 第K的的奇数，没有则返回0；
	 */
	public int findKth(int[] arr, int k) {
		//筛选出所有奇数，并按照升序排列
		Object[] oddNumArr = getSortedOddNum(arr);
		//判断是否存在第K大的奇数，不存在则返回0
		if(k > oddNumArr.length) {
			return 0;
		}
		//返回第K大的奇数
		return Integer.parseInt(oddNumArr[k - 1].toString());
	}
	
	public static void main(String[] args) {
		//初始化测试数据
		int[] arr = {1,3,7,9,2,3,1,1,4,65,231,90,56};
		int k = 6;
		
		GetOddNumByKth oddNum = new GetOddNumByKth();
		//打印结果
		System.out.println("从小到大排列，第 " + k + "个奇数是 ：" + oddNum.findKth(arr, k));
		//打印时间复杂度
		System.out.println("时间复杂度为：" + oddNum.timeComplexity(arr));
	}

}
