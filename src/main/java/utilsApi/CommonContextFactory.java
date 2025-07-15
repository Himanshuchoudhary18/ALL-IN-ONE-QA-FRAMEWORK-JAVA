package utilsApi;

public class CommonContextFactory {
    private static final ThreadLocal<CommonContext> commonContextThreadLocal = new ThreadLocal<CommonContext>() {
        public CommonContext initialValue() {
            return CommonContext.getInstance();
        }
    };

    public CommonContextFactory() {
    }

    public static CommonContext getCommonContext() {
        return commonContextThreadLocal.get();
    }
}
