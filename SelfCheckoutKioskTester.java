//////////////// Self_Checkout_Kiosk /////////////////////////
//
// Title: In this application we are going to develop a simple self-checkout application for a
// grocery store.
//
// Author: Che John
// Email: cbfu@wisc.edu

public class SelfCheckoutKioskTester {


  /**
   * Checks whether SelfCheckoutKisok.getItemName() and SelfCheckoutKisok.getItemPrice() method work
   * as expected.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testItemNameAndPriceGetterMethods() {
    // consider all identifiers values as input arguments
    // GROCERY_ITEMS array is a perfect size array. So, its elements are stored
    // in the range of indezes from 0 .. GROCERY_ITEMS.length -1
    for (int i = 0; i < SelfCheckoutKiosk.GROCERY_ITEMS.length; i++) {
      // check first for the correctness of the getItemName(i) method
      if (!SelfCheckoutKiosk.getItemName(i).equals(SelfCheckoutKiosk.GROCERY_ITEMS[i][0])) {
        System.out.println("Problem detected: Called your getItemName() method with "
            + "input value " + i + ". But it did not return the expected output.");
        return false;
      }

      // Now, let's check for the correctness of the getItemPrice(i) method
      double expectedPriceOutput =
          Double.valueOf(SelfCheckoutKiosk.GROCERY_ITEMS[i][1].substring(1).trim());
      // We do not use == to compare floating-point numbers (double and float)
      // in java. Two variables a and b of type double are equal if the absolute
      // value of their difference is less or equal to a small threshold epsilon.
      // For instance, if Math.abs(a - b) <= 0.001, then a equals b
      if (Math.abs((SelfCheckoutKiosk.getItemPrice(i) - expectedPriceOutput)) > 0.001) {
        // you can print a descriptive error message before returning false
        return false;
      }
    }
    return true; // No defect detected -> The implementation passes this test
  }


  /**
   * Checks the correctness of SelfCheckoutKiosk.addItemToBaggingArea() method
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddItemToBaggingArea() {
    // Create an empty bagging area
    String[] items = new String[10];
    int size = 0;

    // Define the test scenarios:

    // (1) Add one item to an empty bagging area
    // try to add an apple (id: 0) to the bagging area
    size = SelfCheckoutKiosk.addItemToBaggingArea(0, items, size);
    if (size != 1) {
      System.out.println("Problem detected: Tried to add one item to an empty, "
          + "bagging area. The returned size must be 1. But your addItemToBaggingArea "
          + "method returned a different output.");
      return false;
    }
    if (!items[0].equals(SelfCheckoutKiosk.getItemName(0))) {
      // notice here the importance of checking for the correctness of your getItemName()
      // method before calling it above
      System.out.println("Problem detected: Tried to add only one item to an empty, "
          + "bagging area. But that item was not appropriately added to the contents "
          + "of the items array.");
    }

    // (2) Consider a non-empty bagging area
    items = new String[] {"Milk", "Chocolate", "Onion", null, null, null, null};
    size = 3;
    size = SelfCheckoutKiosk.addItemToBaggingArea(10, items, size);
    if (size != 4) {
      System.out.println("Problem detected: Tried to add only one item to an non-empty, "
          + "bagging area. The size must be incremented after the method returns. But "
          + "it was not the case");
      return false;
    }
    if (!items[3].equals(SelfCheckoutKiosk.getItemName(10))) {
      System.out.println("Problem detected: Tried to add one item to an non-empty, "
          + "bagging area. But that item was not appropriately added to the contents "
          + "of the items array.");
    }

    // (3) Consider adding an item to a full bagging are
    items = new String[] {"Pizza", "Eggs", "Apples"};
    size = 3;
    size = SelfCheckoutKiosk.addItemToBaggingArea(2, items, size);
    // TODO Complete the implementation of this test scenario
    // Check that the returned size is correct (must be 3), and that no
    // changes have been made to the content of items array {"Pizza", "Eggs", "Apples"}


    return true; // No defects detected by this unit test
  }


  // Checks the correctness of SelfCheckoutKiosk.count() method
  // Try to consider different test scenarios: (1) a bagging area (defined by
  // the items array and its size) which contains 0 occurrences of the item,
  // (2) a bagging area which contains at least 4 items and only one occurrence
  // of the item to count, and (3) a bagging area which contains at least 5 items
  // and 2 occurrences of the item to count.
  public static boolean testCount() {
    String[] baggingArea = {"Banana", "Beef", "Blueberry", "Cheese", "Butter", "Cheese", "Cereal",
        "Cheese", "Chicken", "Cheese", "Cookie"};
    // No occurrence (Avocado)
    int count = SelfCheckoutKiosk.count("Avocado", baggingArea, 11);
    if (count != 0) {
      System.out.println("Failed test 1");
      return false;
    }
    // 4 items 1 occurrence (Cereal)
    String[] baggingArea2 = {"Butter", "Cheese", "Cereal", "Chicken"};
    int count2 = SelfCheckoutKiosk.count("Cereal", baggingArea2, 4);
    if (count2 != 1) {
      System.out.println("Failed test 2");
      return false;
    }
    // 5 items 4 occurrences (Cheese)
    String[] baggingArea3 = {"Cheese", "Cheese", "Cheese", "Cheese", "Chicken"};
    int count3 = SelfCheckoutKiosk.count("Cheese", baggingArea3, 5);
    if (count3 != 4) {
      System.out.println("Failed test 3");
      return false;
    }
    System.out.println("Passed all tests");
    return true;
  }

  // Checks the correctness of SelfCheckoutKiosk.indexOf() method
  // Consider the cases where the items array contains at least one match
  // with the item to find, and the case when the item was not stored in
  // the array and the expected output is -1
  public static boolean testIndexOf() {
    String[] baggingArea = {"Banana", "Beef", "Blueberry", "Cheese", "Butter", "Cheese", "Cereal",
        "Cheese", "Chicken", "Cheese", "Cookie"};
    // No occurrence (Avocado)
    int index = SelfCheckoutKiosk.indexOf("Avocado", baggingArea, 11);
    if (index != -1) {
      System.out.println("Failed test 1");
      return false;
    }
    // 4 items 1 occurrence (Cereal)
    String[] baggingArea2 = {"Butter", "Cheese", "Cereal", "Chicken"};
    int index2 = SelfCheckoutKiosk.indexOf("Cereal", baggingArea2, 4);
    if (index2 != 2) {
      System.out.println("Failed test 2");
      return false;
    }
    // 5 items 4 occurrences (Cheese)
    String[] baggingArea3 = {"Cheese", "Cheese", "Cheese", "Cheese", "Chicken"};
    int index3 = SelfCheckoutKiosk.indexOf("Cheese", baggingArea3, 5);
    if (index3 != 0) {
      System.out.println("Failed test 3");
      return false;
    }
    System.out.println("Passed all tests");
    return true;
  }

  // Checks that when only one attempt to remove an item stored in the bagging area
  // is made, only one occurrence of that item is removed from the array of items,
  // that the returned size is correct, and that the items array contains all the
  // other items.
  public static boolean testRemove() {
    String[] baggingArea = {"Banana", "Beef", "Blueberry", "Cheese", "Butter", "Cheese", "Cereal",
        "Cheese", "Chicken", "Cheese", "Cookie"};
    // No occurrence (Avocado)
    int removeI = SelfCheckoutKiosk.remove("Avocado", baggingArea, 11);
    if (removeI != -1) {
      System.out.println("Failed test 1");
      return false;
    }
    // 4 items 1 occurrence (Cereal)
    String[] baggingArea2 = {"Butter", "Cheese", "Cereal", "Chicken"};
    int removeI2 = SelfCheckoutKiosk.remove("Cereal", baggingArea2, 4);
    if (removeI2 != 3) {
      System.out.println("Failed test 2");
      return false;
    }
    // 5 items 4 occurrences (Cheese)
    String[] baggingArea3 = {"Cheese", "Cheese", "Cheese", "Cheese", "Chicken"};
    int removeI3 = SelfCheckoutKiosk.remove("Cheese", baggingArea3, 5);
    if (removeI3 != 4) {
      System.out.println("Failed test 3");
      return false;
    }
    System.out.println("Passed all tests");
    return true;
  }


  // Checks whether getSubTotalPrice method returns the correct output
  public static boolean testGetSubTotalPrice() {
    // a list of different items
    String[] list1 = {"Cheese", "Banana", "Cheese", "Beef", "Blueberry"};

    String[] itemSet = new String[list1.length];

    double dupN1 = SelfCheckoutKiosk.getSubTotalPrice(list1, 5);
    // String dupN1S=Double.toString(dupN1);
    if (!((dupN1 - 18.15) < 0.0000001)) {
      System.out.println("Failed test 1");
      return false;
    }

    // 4 items 1 occurrence (Cereal)
    String[] itemSet2 = new String[list1.length];
    String[] list2 = {"Butter", "Cheese", "Cereal", "Chicken"};
    double dupN2 = SelfCheckoutKiosk.getSubTotalPrice(list2, 4);

    if (!((dupN2 - 16.86) < 0.0000001)) {
      System.out.println("Failed test 2");
      return false;
    }

    // 5 items 4 occurrences (Cheese)
    String[] itemSet3 = new String[list1.length];
    String[] list3 = {"Cheese", "Cheese", "Cheese"};

    double dupN3 = SelfCheckoutKiosk.getSubTotalPrice(list3, 3);

    if (!((dupN3 - 10.47) < 0.0000001)) {
      System.out.println("Failed test 3");
      return false;
    }
    System.out.println("Passed all tests");
    return true;
  }

  // Checks whether getUniqueCheckedOutput functionning is correct
  public static boolean testGetUniqueCheckedOutItems() {
    String[] baggingArea = {"Cheese", "Banana", "Cheese", "Beef", "Blueberry", "Butter", "Melon",
        "Cereal", "Mango", "Chicken", "Cookie"};

    String[] itemSet = new String[baggingArea.length];

    // No occurrence (Avocado)
    int dupN = SelfCheckoutKiosk.getUniqueCheckedOutItems(baggingArea, 11, itemSet);
    if (dupN != 10) {
      System.out.println("Failed test 1");
      return false;
    }

    // 4 items 1 occurrence (Cereal)
    String[] itemSet2 = new String[baggingArea.length];
    String[] baggingArea2 = {"Butter", "Cheese", "Cereal", "Chicken"};
    int dupN2 = SelfCheckoutKiosk.getUniqueCheckedOutItems(baggingArea2, 4, itemSet2);
    if (dupN2 != 4) {
      System.out.println("Failed test 2");
      return false;
    }

    // 5 items 4 occurrences (Cheese)
    String[] itemSet3 = new String[baggingArea.length];
    String[] baggingArea3 = {"Cheese", "Cheese", "Cheese", "Cheese", "Chicken"};
    int dupN3 = SelfCheckoutKiosk.getUniqueCheckedOutItems(baggingArea3, 5, itemSet3);
    if (dupN3 != 2) {
      System.out.println("Failed test 3");
      return false;
    }
    System.out.println("Passed all tests");
    return true;
  }

  /**
   * main method used to call the unit tests
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    // System.out.println("tesItemNameAndPriceGetterMethods(): " +
    // testItemNameAndPriceGetterMethods());
    // SelfCheckoutKiosk.printCatalog();
    // testAddItemToBaggingArea();
    // testCount();
    // testIndexOf();
    // testRemove();
    // testGetUniqueCheckedOutItems();
    testGetSubTotalPrice();
  }


}
