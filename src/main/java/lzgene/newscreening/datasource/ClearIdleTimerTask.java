package lzgene.newscreening.datasource;

import java.util.TimerTask;

/**    5)     定时器任务ClearIdleTimerTask用于定时清除空闲的数据源
 *
 * 清除空闲连接任务。
 *
 * @author elon
 * @version 2018年2月26日
 */
public class ClearIdleTimerTask extends TimerTask {
    @Override
    public void run() {
        DDSHolder.instance().clearIdleDDS();
    }
}
