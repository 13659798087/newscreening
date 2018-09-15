package lzgene.newscreening.schedule;

import lzgene.newscreening.model.UserInfo;
import lzgene.newscreening.services.UserServices;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ClearTimerController {

    @Resource
    private UserServices userServices;

    @Value("${dayLimitLoginError}")
    private Integer dayLimitLoginError;

    /**
     * 每天定时将用户每天登录错误次数<=2次的字段重置为0
     */
    @Scheduled(cron = "${jobs.schedule}")
    public void resetDayLoginError(){
        userServices.resetDayLoginError(dayLimitLoginError);
    }

}
