package cn.aurora_x.android.calculator.presenter;

import cn.aurora_x.android.calculator.model.biz.ScienceCalculator;
import cn.aurora_x.android.calculator.view.MainActivity;
import cn.aurora_x.android.calculator.view.ScienceFragment;

/**
 * Created by Rinko on 2016/11/15.
 */
public class ScienceFragmentPresenter {
    private ScienceFragment fragment;
    private static ScienceFragmentPresenter instance;

    private ScienceFragmentPresenter() {
    }

    public static ScienceFragmentPresenter getInstance() {
        if (instance == null) {
            instance = new ScienceFragmentPresenter();
        }
        return instance;
    }

    public void setFragment(ScienceFragment fragment) {
        this.fragment = fragment;
    }

    public void onButtonDelClick() {
        String screen = ScienceCalculator.del(fragment.getScreen());
        fragment.setScreen(screen);
    }

    public void onButtonEqualsClick() {
        String exp = fragment.getScreen();
        ScienceCalculator.calculate(exp, fragment.getContext(), new ScienceCalculator.OnCalculateFinish() {
            @Override
            public void success(String res) {
                fragment.setScreen(res);
            }

            @Override
            public void fail(String msg) {
                fragment.throwException(msg);
            }
        });
    }
}
