package qqduan.test.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
/*
 * 抽象测试过程类
 */
public abstract class AbsTestProcess {
	private String name;
	ProcessGroup group;

	public AbsTestProcess() {
		super();
		this.name = getClass().getSimpleName();
	}

	public abstract ProcessGroup tmplates();

	public abstract void setData(Map<String, Map<String, Map<String, String>>> groupInparam,
			Map<String, Map<String, Map<String, String>>> groupExparam);

	public void test() {
		System.out.println("============================");
		System.out.println("开始测试：" + name);
		this.group = tmplates();
		if (group != null) {
			Map<String, Map<String, Map<String, String>>> groupInparam = new HashMap<>();
			Map<String, Map<String, Map<String, String>>> groupExparam = new HashMap<>();
			this.setData(groupInparam, groupExparam);
			Iterator<Entry<String, ProcessChain>> it = group.chains.entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, ProcessChain> next = it.next();
				String chainName = next.getKey();
				Map<String, Map<String, String>> chainInparam = groupInparam.get(chainName);
				Map<String, Map<String, String>> chainExparam = groupExparam.get(chainName);
				group.addData(chainName, chainInparam, chainExparam);
			}
			long start=System.currentTimeMillis();
			group.ontest();
			long end=System.currentTimeMillis();
			System.out.println("测试结果:" + this.group.isSuccess+"  总共耗时"+(end-start)+"ms");
		}
	}
}
