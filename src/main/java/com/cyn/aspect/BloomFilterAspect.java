package com.cyn.aspect;

import com.cyn.annotation.BloomFilterAnno;
import com.cyn.component.BloomFilterComponent;
import com.cyn.constant.SystemConstant;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author G0dc
 * @description:
 * @date 2022/11/3 13:34
 */
@Component
@Aspect //标识为切面
public class BloomFilterAspect {
    /**
     * 定义切点
     */
    @Pointcut("@annotation(com.cyn.annotation.BloomFilterAnno)")
    public void pt() {
    }

    /**
     * 难点：获取被增强方法上的注解对象
     *
     * @param proceedingJoinPoint 被增强方法的信息封装成的对象
     * @return
     */
    @Around("pt()")
    public Object bloomFilter(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String id = getId(proceedingJoinPoint);
        // proceed为返回值
        boolean isExist = before(proceedingJoinPoint, id);
        String result = "404 not found";
        if (isExist) result = (String) proceedingJoinPoint.proceed();
        return result;
    }

    @Autowired
    private BloomFilterComponent bloomFilterComponent;

    /**
     * 前置增强逻辑
     *
     * @param proceedingJoinPoint
     * @param id
     * @return
     */
    private boolean before(ProceedingJoinPoint proceedingJoinPoint, String id) {
        boolean flag = false;
        // 获取注解值
        String key = getAnnoValue(proceedingJoinPoint);
        // 健壮性判断
        if (key == null || key.length() == 0) {
            throw new RuntimeException("key不能为空");
        }
        // 获取参数
        if (SystemConstant.USER.equals(key)) {
            return flag = bloomFilterComponent.isUserExist(id);
        }
        return flag;
    }

    /**
     * 获取方法形参 即 id
     *
     * @param proceedingJoinPoint
     * @return
     */
    private String getId(ProceedingJoinPoint proceedingJoinPoint) {
        Object[] args = proceedingJoinPoint.getArgs();
        int length = args.length;
        Integer id = (Integer) args[length - 1];
        return id.toString();
    }

    /**
     * 获取注解的值
     *
     * @param proceedingJoinPoint
     * @return
     */
    private String getAnnoValue(ProceedingJoinPoint proceedingJoinPoint) {
        // 相当于把被代理方法封装成对象
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        BloomFilterAnno cacheKey = signature.getMethod().getAnnotation(BloomFilterAnno.class);
        // 获取注解参数信息
        return cacheKey.key();
    }

}
