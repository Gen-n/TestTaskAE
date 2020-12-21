package framework.assertions;

import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class Assertions extends SoftAssert {
    
    @Step
    public void executeAssert(IAssert<?> iAssert) {
        super.executeAssert(iAssert);
    }

    @Step
    public void onAssertSuccess(IAssert<?> iAssert) {
        super.onAssertSuccess(iAssert);
    }

    @Step
    public void onAssertFailure(IAssert<?> iAssert, AssertionError assertionError) {
        super.onAssertFailure(iAssert, assertionError);
    }

    @Step
    public void onBeforeAssert(IAssert<?> iAssert) {
        super.onBeforeAssert(iAssert);
    }

    @Step
    public void onAfterAssert(IAssert<?> iAssert) {
        super.onAfterAssert(iAssert);
    }

    @Step
    public void assertTrue(boolean b, String s) {
        super.assertTrue(b, s);
    }

    @Step
    public void assertTrue(boolean b) {
        super.assertTrue(b);
    }

    @Step
    public void assertFalse(boolean b, String s) {
        super.assertFalse(b, s);
    }

    @Step
    public void assertFalse(boolean b) {
        super.assertFalse(b);
    }

    @Step
    public void fail(String s, Throwable throwable) {
        super.fail(s, throwable);
    }

    @Step
    public void fail(String s) {
        super.fail(s);
    }

    @Step
    public void fail() {
        super.fail();
    }

    @Step
    public <T> void assertEquals(T t, T t1, String s) {
        super.assertEquals(t, t1, s);
    }

    @Step
    public <T> void assertEquals(T t, T t1) {
        super.assertEquals(t, t1);
    }

    @Step
    public void assertEquals(String s, String s1, String s2) {
        super.assertEquals(s, s1, s2);
    }

    @Step
    public void assertEquals(String s, String s1) {
        super.assertEquals(s, s1);
    }

    @Step
    public void assertEquals(double v, double v1, double v2, String s) {
        super.assertEquals(v, v1, v2, s);
    }

    @Step
    public void assertEquals(double v, double v1, double v2) {
        super.assertEquals(v, v1, v2);
    }

    @Step
    public void assertEquals(float v, float v1, float v2, String s) {
        super.assertEquals(v, v1, v2, s);
    }

    @Step
    public void assertEquals(float v, float v1, float v2) {
        super.assertEquals(v, v1, v2);
    }

    @Step
    public void assertEquals(long l, long l1, String s) {
        super.assertEquals(l, l1, s);
    }

    @Step
    public void assertEquals(long l, long l1) {
        super.assertEquals(l, l1);
    }

    @Step
    public void assertEquals(boolean b, boolean b1, String s) {
        super.assertEquals(b, b1, s);
    }

    @Step
    public void assertEquals(boolean b, boolean b1) {
        super.assertEquals(b, b1);
    }

    @Step
    public void assertEquals(byte b, byte b1, String s) {
        super.assertEquals(b, b1, s);
    }

    @Step
    public void assertEquals(byte b, byte b1) {
        super.assertEquals(b, b1);
    }

    @Step
    public void assertEquals(char c, char c1, String s) {
        super.assertEquals(c, c1, s);
    }

    @Step
    public void assertEquals(char c, char c1) {
        super.assertEquals(c, c1);
    }

    @Step
    public void assertEquals(short i, short i1, String s) {
        super.assertEquals(i, i1, s);
    }

    @Step
    public void assertEquals(short i, short i1) {
        super.assertEquals(i, i1);
    }

    @Step
    public void assertEquals(int i, int i1, String s) {
        super.assertEquals(i, i1, s);
    }

    @Step
    public void assertEquals(int i, int i1) {
        super.assertEquals(i, i1);
    }

    @Step
    public void assertNotNull(Object o) {
        super.assertNotNull(o);
    }

    @Step
    public void assertNotNull(Object o, String s) {
        super.assertNotNull(o, s);
    }

    @Step
    public void assertNull(Object o) {
        super.assertNull(o);
    }

    @Step
    public void assertNull(Object o, String s) {
        super.assertNull(o, s);
    }

    @Step
    public void assertSame(Object o, Object o1, String s) {
        super.assertSame(o, o1, s);
    }

    @Step
    public void assertSame(Object o, Object o1) {
        super.assertSame(o, o1);
    }

    @Step
    public void assertNotSame(Object o, Object o1, String s) {
        super.assertNotSame(o, o1, s);
    }

    @Step
    public void assertNotSame(Object o, Object o1) {
        super.assertNotSame(o, o1);
    }

    @Step
    public void assertEquals(Collection<?> collection, Collection<?> collection1) {
        super.assertEquals(collection, collection1);
    }

    @Step
    public void assertEquals(Collection<?> collection, Collection<?> collection1, String s) {
        super.assertEquals(collection, collection1, s);
    }

    @Step
    public void assertEquals(Object[] objects, Object[] objects1, String s) {
        super.assertEquals(objects, objects1, s);
    }

    @Step
    public void assertEqualsNoOrder(Object[] objects, Object[] objects1, String s) {
        super.assertEqualsNoOrder(objects, objects1, s);
    }

    @Step
    public void assertEquals(Object[] objects, Object[] objects1) {
        super.assertEquals(objects, objects1);
    }

    @Step
    public void assertEqualsNoOrder(Object[] objects, Object[] objects1) {
        super.assertEqualsNoOrder(objects, objects1);
    }

    @Step
    public void assertEquals(byte[] bytes, byte[] bytes1) {
        super.assertEquals(bytes, bytes1);
    }

    @Step
    public void assertEquals(byte[] bytes, byte[] bytes1, String s) {
        super.assertEquals(bytes, bytes1, s);
    }

    @Step
    public void assertEquals(Set<?> set, Set<?> set1) {
        super.assertEquals(set, set1);
    }

    @Step
    public void assertEquals(Set<?> set, Set<?> set1, String s) {
        super.assertEquals(set, set1, s);
    }

    @Step
    public void assertEquals(Map<?, ?> map, Map<?, ?> map1) {
        super.assertEquals(map, map1);
    }

    @Step
    public void assertNotEquals(Object o, Object o1, String s) {
        super.assertNotEquals(o, o1, s);
    }

    @Step
    public void assertNotEquals(Object o, Object o1) {
        super.assertNotEquals(o, o1);
    }

    @Step
    public void assertNotEquals(String s, String s1, String s2) {
        super.assertNotEquals(s, s1, s2);
    }

    @Step
    public void assertNotEquals(String s, String s1) {
        super.assertNotEquals(s, s1);
    }

    @Step
    public void assertNotEquals(long l, long l1, String s) {
        super.assertNotEquals(l, l1, s);
    }

    @Step
    public void assertNotEquals(long l, long l1) {
        super.assertNotEquals(l, l1);
    }

    @Step
    public void assertNotEquals(boolean b, boolean b1, String s) {
        super.assertNotEquals(b, b1, s);
    }

    @Step
    public void assertNotEquals(boolean b, boolean b1) {
        super.assertNotEquals(b, b1);
    }

    @Step
    public void assertNotEquals(byte b, byte b1, String s) {
        super.assertNotEquals(b, b1, s);
    }

    @Step
    public void assertNotEquals(byte b, byte b1) {
        super.assertNotEquals(b, b1);
    }

    @Step
    public void assertNotEquals(char c, char c1, String s) {
        super.assertNotEquals(c, c1, s);
    }

    @Step
    public void assertNotEquals(char c, char c1) {
        super.assertNotEquals(c, c1);
    }

    @Step
    public void assertNotEquals(short i, short i1, String s) {
        super.assertNotEquals(i, i1, s);
    }

    @Step
    public void assertNotEquals(short i, short i1) {
        super.assertNotEquals(i, i1);
    }

    @Step
    public void assertNotEquals(int i, int i1, String s) {
        super.assertNotEquals(i, i1, s);
    }

    @Step
    public void assertNotEquals(int i, int i1) {
        super.assertNotEquals(i, i1);
    }

    @Step
    public void assertNotEquals(float v, float v1, float v2, String s) {
        super.assertNotEquals(v, v1, v2, s);
    }

    @Step
    public void assertNotEquals(float v, float v1, float v2) {
        super.assertNotEquals(v, v1, v2);
    }

    @Step
    public void assertNotEquals(double v, double v1, double v2, String s) {
        super.assertNotEquals(v, v1, v2, s);
    }

    @Step
    public void assertNotEquals(double v, double v1, double v2) {
        super.assertNotEquals(v, v1, v2);
    }
}
