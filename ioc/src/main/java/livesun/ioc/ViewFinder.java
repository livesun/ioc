package livesun.ioc;

import android.app.Activity;
import android.view.View;

/**
 * Created by 29028 on 2017/5/17.
 */

public class ViewFinder {

    private Activity mActivity;
    private View mView;

    public ViewFinder(Activity activity) {
        mActivity = activity;
    }

    public ViewFinder(View view) {
        mView = view;
    }

    public View findViewById(int id){
        return mActivity==null?mView.findViewById(id):mActivity.findViewById(id);
    }
}
