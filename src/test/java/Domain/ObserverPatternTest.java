package Domain;

import Data.JavaByteCodeAdapter.ClassNode;
import Data.JavaByteCodeAdapter.MethodNode;

import Domain.Rules.ObserverPatternRule;
import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ObserverPatternTest {

    public final static String[] listForAdd = new String[]{"observer", "add", "register", "attach", "append", "join", "connect", "create", "produce", "push"};
    public final static String[] listForRemove = new String[]{"observer", "remove", "delete", "detach", "separate", "disconnect", "take", "pull", "peel"};
    public final static String[] listForNotify = new String[]{"observer", "inform", "notify", "tell", "advise", "alert", "warn", "report", "communicate", "send"};

    @Test
    public void addIssueDepInvPrincipleInfo () {
        ObserverPatternRule rule = new ObserverPatternRule();
        Issue expected = new Issue("ObserverIdentified", -1, "class.java", "class", "Observer Pattern Found", Severity.INFO);
        Issue actual = rule.createIssue("ObserverIdentified", -1, "class.java", "class", "Observer Pattern Found", "INFO");

        Assertions.assertEquals(expected.rule, actual.rule);
        Assertions.assertEquals(expected.file, actual.file);
        Assertions.assertEquals(expected.classValue, actual.classValue);
        Assertions.assertEquals(expected.message, actual.message);
    }

    @Test
    public void methodContainsItemFromListAddOneTest () {
        ObserverPatternRule rule = new ObserverPatternRule();

        boolean expected = true;
        boolean actual = rule.stringContainsItemFromList("registerObserver", listForAdd);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void methodContainsItemFromListAddTwoTest () {
        ObserverPatternRule rule = new ObserverPatternRule();

        boolean expected = true;
        boolean actual = rule.stringContainsItemFromList("addObserver", listForAdd);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void methodContainsItemFromListAddThreeTest () {
        ObserverPatternRule rule = new ObserverPatternRule();

        boolean expected = true;
        boolean actual = rule.stringContainsItemFromList("createObserver", listForAdd);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void methodContainsItemFromListAddFourTest () {
        ObserverPatternRule rule = new ObserverPatternRule();

        boolean expected = true;
        boolean actual = rule.stringContainsItemFromList("connectObserver", listForAdd);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void methodContainsItemFromListAddFiveTest () {
        ObserverPatternRule rule = new ObserverPatternRule();

        boolean expected = true;
        boolean actual = rule.stringContainsItemFromList("produceObserver", listForAdd);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void methodContainsItemFromListRemoveOneTest () {
        ObserverPatternRule rule = new ObserverPatternRule();

        boolean expected = true;
        boolean actual = rule.stringContainsItemFromList("detachObserver", listForRemove);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void methodContainsItemFromListRemoveTwoTest () {
        ObserverPatternRule rule = new ObserverPatternRule();

        boolean expected = true;
        boolean actual = rule.stringContainsItemFromList("removeObserver", listForRemove);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void methodContainsItemFromListRemoveThreeTest () {
        ObserverPatternRule rule = new ObserverPatternRule();

        boolean expected = true;
        boolean actual = rule.stringContainsItemFromList("disconnectObserver", listForRemove);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void methodContainsItemFromListRemoveFourTest () {
        ObserverPatternRule rule = new ObserverPatternRule();

        boolean expected = true;
        boolean actual = rule.stringContainsItemFromList("pullObserver", listForRemove);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void methodContainsItemFromListRemoveFiveTest () {
        ObserverPatternRule rule = new ObserverPatternRule();

        boolean expected = true;
        boolean actual = rule.stringContainsItemFromList("separateObserver", listForRemove);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void methodContainsItemFromListNotifyOneTest () {
        ObserverPatternRule rule = new ObserverPatternRule();

        boolean expected = true;
        boolean actual = rule.stringContainsItemFromList("sendToObserver", listForNotify);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void methodContainsItemFromListNotifyTwoTest () {
        ObserverPatternRule rule = new ObserverPatternRule();

        boolean expected = true;
        boolean actual = rule.stringContainsItemFromList("informObserver", listForNotify);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void methodContainsItemFromListNotifyThreeTest () {
        ObserverPatternRule rule = new ObserverPatternRule();

        boolean expected = true;
        boolean actual = rule.stringContainsItemFromList("notifyObserver", listForNotify);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void methodContainsItemFromListNotifyFourTest () {
        ObserverPatternRule rule = new ObserverPatternRule();

        boolean expected = true;
        boolean actual = rule.stringContainsItemFromList("reportToObserver", listForNotify);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void methodContainsItemFromListNotifyFiveTest () {
        ObserverPatternRule rule = new ObserverPatternRule();

        boolean expected = true;
        boolean actual = rule.stringContainsItemFromList("tellObserver", listForNotify);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void methodContainsItemFromListNotAMethodOneTest () {
        ObserverPatternRule rule = new ObserverPatternRule();

        boolean expected = false;
        boolean actual = rule.stringContainsItemFromList("produceObserver", listForRemove);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void methodContainsItemFromListNotAMethodTwoTest () {
        ObserverPatternRule rule = new ObserverPatternRule();

        boolean expected = false;
        boolean actual = rule.stringContainsItemFromList("onObserver", listForRemove);

        Assertions.assertEquals(expected, actual);
    }

    // False case for methodContainsObserverArgument
    @Test
    public void methodContainsObserverArgumentTestOneFalse () {
        ClassNode classOne = EasyMock.mock(ClassNode.class);
        ClassNode classTwo = EasyMock.mock(ClassNode.class);
        ClassNode classThree = EasyMock.mock(ClassNode.class);
        ClassNode classFour = EasyMock.mock(ClassNode.class);

        List<ClassNode> classes = new ArrayList<>();
        classes.add(classOne);
        classes.add(classTwo);
        classes.add(classThree);
        classes.add(classFour);

        EasyMock.expect(classOne.getClassName()).andReturn("classOne");
        EasyMock.expect(classTwo.getClassName()).andReturn("classTwo");
        EasyMock.expect(classThree.getClassName()).andReturn("classThree");
        EasyMock.expect(classFour.getClassName()).andReturn("classFour");

        EasyMock.replay(classOne, classTwo, classThree, classFour);

        ObserverPatternRule rule = new ObserverPatternRule();

        boolean expected = false;
        boolean actual = rule.methodContainsObserverArgument(classes);

        EasyMock.verify(classOne, classTwo, classThree, classFour);

        Assertions.assertEquals(expected, actual);
    }

    // False case for methodContainsObserverArgument
    @Test
    public void methodContainsObserverArgumentTestTwoFalse () {
        ClassNode classOne = EasyMock.mock(ClassNode.class);
        ClassNode classTwo = EasyMock.mock(ClassNode.class);
        ClassNode classThree = EasyMock.mock(ClassNode.class);
        ClassNode classFour = EasyMock.mock(ClassNode.class);
        ClassNode classFive = EasyMock.mock(ClassNode.class);
        ClassNode classSix = EasyMock.mock(ClassNode.class);
        ClassNode classSeven = EasyMock.mock(ClassNode.class);
        ClassNode classEight = EasyMock.mock(ClassNode.class);
        ClassNode classNine = EasyMock.mock(ClassNode.class);
        ClassNode classTen = EasyMock.mock(ClassNode.class);
        ClassNode classEleven = EasyMock.mock(ClassNode.class);
        ClassNode classTwelve = EasyMock.mock(ClassNode.class);

        List<ClassNode> classes = new ArrayList<>();
        classes.add(classOne);
        classes.add(classTwo);
        classes.add(classThree);
        classes.add(classFour);
        classes.add(classFive);
        classes.add(classSix);
        classes.add(classSeven);
        classes.add(classEight);
        classes.add(classNine);
        classes.add(classTen);
        classes.add(classEleven);
        classes.add(classTwelve);

        EasyMock.expect(classOne.getClassName()).andReturn("classOne");
        EasyMock.expect(classTwo.getClassName()).andReturn("classTwo");
        EasyMock.expect(classThree.getClassName()).andReturn("classThree");
        EasyMock.expect(classFour.getClassName()).andReturn("classFour");
        EasyMock.expect(classFive.getClassName()).andReturn("classFive");
        EasyMock.expect(classSix.getClassName()).andReturn("classSix");
        EasyMock.expect(classSeven.getClassName()).andReturn("classSeven");
        EasyMock.expect(classEight.getClassName()).andReturn("classEight");
        EasyMock.expect(classNine.getClassName()).andReturn("classNine");
        EasyMock.expect(classTen.getClassName()).andReturn("classTen");
        EasyMock.expect(classEleven.getClassName()).andReturn("classEleven");
        EasyMock.expect(classTwelve.getClassName()).andReturn("classTwelve");

        EasyMock.replay(classOne, classTwo, classThree, classFour, classFive, classSix, classSeven, classEight, classNine, classTen, classEleven, classTwelve);

        ObserverPatternRule rule = new ObserverPatternRule();

        boolean expected = false;
        boolean actual = rule.methodContainsObserverArgument(classes);

        EasyMock.verify(classOne, classTwo, classThree, classFour);

        Assertions.assertEquals(expected, actual);
    }

    // True case for methodContainsObserverArgument
    @Test
    public void methodContainsObserverArgumentTestOneTrue() {
        ClassNode mockClassNode = EasyMock.mock(ClassNode.class);

        List<ClassNode> arguments = new ArrayList<>();
        arguments.add(mockClassNode);

        EasyMock.expect(mockClassNode.getClassName()).andReturn("ObserverClass").times(1);

        EasyMock.replay(mockClassNode);

        ObserverPatternRule rule = new ObserverPatternRule();

        boolean expected = true;
        boolean actual = rule.methodContainsObserverArgument(arguments);

        EasyMock.verify(mockClassNode);

        Assertions.assertEquals(expected, actual);

    }

    // True case for methodContainsObserverArgument
    @Test
    public void methodContainsObserverArgumentTestTwoTrue() {
        ClassNode classOne = EasyMock.mock(ClassNode.class);
        ClassNode classTwo = EasyMock.mock(ClassNode.class);
        ClassNode classThree = EasyMock.mock(ClassNode.class);
        ClassNode classFour = EasyMock.mock(ClassNode.class);
        ClassNode classFive = EasyMock.mock(ClassNode.class);
        ClassNode classSix = EasyMock.mock(ClassNode.class);
        ClassNode classSeven = EasyMock.mock(ClassNode.class);
        ClassNode classEight = EasyMock.mock(ClassNode.class);
        ClassNode classNine = EasyMock.mock(ClassNode.class);
        ClassNode classTen = EasyMock.mock(ClassNode.class);
        ClassNode classEleven = EasyMock.mock(ClassNode.class);
        ClassNode classTwelve = EasyMock.mock(ClassNode.class);

        List<ClassNode> classes = new ArrayList<>();
        classes.add(classOne);
        classes.add(classTwo);
        classes.add(classThree);
        classes.add(classFour);
        classes.add(classFive);
        classes.add(classSix);
        classes.add(classSeven);
        classes.add(classEight);
        classes.add(classNine);
        classes.add(classTen);
        classes.add(classEleven);
        classes.add(classTwelve);

        EasyMock.expect(classOne.getClassName()).andReturn("classOne");
        EasyMock.expect(classTwo.getClassName()).andReturn("classTwo");
        EasyMock.expect(classThree.getClassName()).andReturn("classThree");
        EasyMock.expect(classFour.getClassName()).andReturn("classFour");
        EasyMock.expect(classFive.getClassName()).andReturn("classFive");
        EasyMock.expect(classSix.getClassName()).andReturn("classSix");
        EasyMock.expect(classSeven.getClassName()).andReturn("classSeven");
        EasyMock.expect(classEight.getClassName()).andReturn("classEight");
        EasyMock.expect(classNine.getClassName()).andReturn("classNine");
        EasyMock.expect(classTen.getClassName()).andReturn("classTen");
        EasyMock.expect(classEleven.getClassName()).andReturn("classEleven");
        EasyMock.expect(classTwelve.getClassName()).andReturn("ObserverClass");

        EasyMock.replay(classOne, classTwo, classThree, classFour, classFive, classSix, classSeven, classEight, classNine, classTen, classEleven, classTwelve);

        ObserverPatternRule rule = new ObserverPatternRule();

        boolean expected = true;
        boolean actual = rule.methodContainsObserverArgument(classes);

        EasyMock.verify(classOne, classTwo, classThree, classFour);

        Assertions.assertEquals(expected, actual);
    }
}
