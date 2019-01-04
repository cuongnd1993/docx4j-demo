/**
 * Created CoreResponse.java at 10:45:56 AM by hungvq
 * TODO
 */
package research.transform;

import research.common.ResponseDescription;

import java.io.Serializable;

/**
 * @author hungvq
 */
public class CoreResponse implements Serializable {

    private static final long serialVersionUID = -566238632352226194L;
    private boolean status;
    private String message;
    private Object result;

    public CoreResponse() {
    }

    public CoreResponse(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public CoreResponse(Object result) {
        this.status = ResponseDescription.SUCCESS_STATUS;
        this.message = ResponseDescription.SUCCESS_MESSAGE;
        this.result = result;
    }

    public CoreResponse(boolean status, String message, Object result) {
        this.status = status;
        this.message = message;
        this.result = result;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
