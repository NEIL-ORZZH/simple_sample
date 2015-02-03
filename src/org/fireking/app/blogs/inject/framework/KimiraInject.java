package org.fireking.app.blogs.inject.framework;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.fireking.app.blogs.inject.framework.anotation.ContextView;
import org.fireking.app.blogs.inject.framework.anotation.InjectView;

import android.app.Activity;

public class KimiraInject {

	static final String METHOD_SET_CONTENTVIEW = "setContentView";

	static final String METHOD_SET_VIEWID = "findViewById";

	public static void init(Activity activity) {

		initContextView(activity);

		initInjectView(activity);
	}

	private static void initContextView(Activity activity) {
		Class<? extends Activity> clazz = activity.getClass();
		ContextView contextView = clazz.getAnnotation(ContextView.class);
		if (contextView != null) {
			int viewId = contextView.value();

			try {
				Method method = clazz.getMethod(METHOD_SET_CONTENTVIEW,
						int.class);
				method.setAccessible(true);
				method.invoke(activity, viewId);
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}

	private static void initInjectView(Activity activity) {
		Class<? extends Activity> clazz = activity.getClass();
		Field[] field = clazz.getDeclaredFields();
		for (Field fil : field) {
			InjectView injectView = fil.getAnnotation(InjectView.class);
			if (injectView != null) {
				int viewid = injectView.value();
				try {
					Method method = clazz.getMethod(METHOD_SET_VIEWID,
							int.class);
					Object obj = method.invoke(activity, viewid);
					fil.setAccessible(true);
					fil.set(activity, obj);
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
