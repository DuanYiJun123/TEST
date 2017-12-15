package qqduan.test.logmodel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import qqduan.test.core.AbsTestCase;
import qqduan.test.core.Defines;
import qqduan.test.core.ProcessChain;
import qqduan.test.core.ProcessGroup;
import qqduan.test.util.FileUtil;

public class LogService {
	public static LogType logtype = LogType.None;

	private static final ExecutorService THREADPOOL = Executors.newFixedThreadPool(Defines.LOG_COUNT);

	public static void startLog(String logtype) {
		if (logtype != null) {
			String value = logtype.toLowerCase();
			switch (value) {
			case "all":
				LogService.logtype = LogType.All;
				break;

			case "onlyfail":
				LogService.logtype = LogType.Only_fail;

			case "none":
				LogService.logtype = LogType.None;

			default:
				LogService.logtype = LogType.None;
				break;
			}
		}
	}

	public static void logEnd() {
		THREADPOOL.shutdown();
	}

	public static void log(ProcessGroup group) {
		THREADPOOL.execute(() -> {
			mlog(group);
		});
	}

	public static void mlog(ProcessGroup group) {
		if (logtype == null) {
			return;
		}
		boolean isSuccess = group.isSuccess;
		if (group.isSuccess() ? logtype.success() : logtype.fail()) {
			Map<String, ProcessChain> chains = group.getChains();
			Iterator<Entry<String, ProcessChain>> iter = chains.entrySet().iterator();
			while (iter.hasNext()) {
				Entry<String, ProcessChain> next = iter.next();
				AbsTestCase tmp = next.getValue().getHead();
				while (tmp != null) {
					File file = new File(FileUtil.getAppRoot() + File.separator + (isSuccess ? "success" : "fail")
							+ File.separator + group.getName() + File.separator + next.getKey() + File.separator
							+ tmp.getName() + ".txt");

					try (BufferedWriter writer = new BufferedWriter(
							new OutputStreamWriter(new FileOutputStream(file)))) {
						writer.write(tmp.getName());
						writer.newLine();
						writer.write("==========================");
						writer.newLine();
						writer.write("caseInparam: " + tmp.getCaseInparam().toString());
						writer.newLine();
						writer.write("caseExparam: " + tmp.getCaseExparam().toString());
						writer.newLine();
						writer.write("caseOutparam: " + tmp.getCaseResult().getData());
						writer.write("==========================");
						writer.newLine();
						writer.flush();
					} catch (IOException e) {
						e.printStackTrace();
					}
					tmp = tmp.getTo();
				}
			}
		}
	}
}
