package top.testeru.mini.exception;

/**
 * @author www.testeru.top
 * @version 1.0.0
 * @Project top.testeru.mini.exception
 * @Description 用户查找不存在
 * @createTime 2023年03月27日 16:25:03
 */

public class UserNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 4564124491192825748L;

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
