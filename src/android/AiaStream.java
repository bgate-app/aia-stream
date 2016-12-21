package aia-plugin-stream;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class echoes a string called from JavaScript.
 */
public class AiaStream extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("startStream")) {
            this.startStream(callbackContext);
            return true;
        }
        if (action.equals("stopStream")) {
            this.stopStream(callbackContext);
            return true;
        }
      
        return false;
    }
    private void startStream( CallbackContext callbackContext) {
        
    }
    private void stopStream( CallbackContext callbackContext) {
        
    }
    
}
