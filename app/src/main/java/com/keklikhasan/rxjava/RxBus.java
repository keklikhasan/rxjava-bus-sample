package com.keklikhasan.rxjava;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class RxBus {

  private final Subject<Object> bus = PublishSubject.create().toSerialized();

  public RxBus() {
  }

  public void send(Object o) {
    bus.onNext(o);
  }

  public Observable<Object> observe() {
    return bus;
  }

  public <T> Observable<T> observe(final Class<T> c) {
    return bus.filter(new Predicate<Object>() {
      @Override
      public boolean test(Object o) throws Exception {
        return c.isAssignableFrom(o.getClass());
      }
    }).map(new Function<Object, T>() {
      @SuppressWarnings("unchecked")
      @Override
      public T apply(Object o) throws Exception {
        return (T) o;
      }
    });
  }
}