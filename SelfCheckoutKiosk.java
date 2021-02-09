//////////////// FILE HEADER //////////////////////////
//
// Title: SELF CHECKOUT KIOSK 
// Course: CS 300 Spring 2021
//
// Author: Che John
// Email: cbfu@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:  NONE
// Online Sources: https://stackoverflow.com/questions/6016501/parsing-a-currency-string-in-java
// helped me find the replaceAll extension for line 61
//
///////////////////////////////////////////////////////////////////////////////

/**
 * Develop a simple self-checkout application for a
 * grocery store. (not long enough)
 *
 */
public class SelfCheckoutKiosk {

  public static final double TAX_RATE = 0.05; // sales tax

  // a perfect-size two-dimensional array that stores the available items in the grocery store
  // GROCERY_ITEMS[i][0] refers to a String that represents the name of the item
  // identified by index i
  // GROCERY_ITEMS[i][1] refers to a String that represents the unit price of the item
  // identified by index i in dollars.
  public static final String[][] GROCERY_ITEMS = new String[][] {{"Apple", "$1.59"},
      {"Avocado", "$0.59"}, {"Banana", "$0.49"}, {"Beef", "$3.79"}, {"Blueberry", "$6.89"},
      {"Broccoli", "$1.79"}, {"Butter", "$4.59"}, {"Carrot", "$1.19"}, {"Cereal", "$3.69"},
      {"Cheese", "$3.49"}, {"Chicken", "$5.09"}, {"Chocolate", "$3.19"}, {"Cookie", "$9.5"},
      {"Cucumber", "$0.79"}, {"Eggs", "$3.09"}, {"Grape", "$2.29"}, {"Ice Cream", "$5.39"},
      {"Milk", "$2.09"}, {"Mushroom", "$1.79"}, {"Onion", "$0.79"}, {"Pepper", "$1.99"},
      {"Pizza", "$11.5"}, {"Potato", "$0.69"}, {"Spinach", "$3.09"}, {"Tomato", "$1.79"}};


  /**
   * Returns the name of the item given its index index - unique identifier of an item
   * 
   * @param index index of item
   * @return name of item
   */
  public static String getItemName(int index) {
    String iName = GROCERY_ITEMS[index][0];
    return iName;
  }

  /**
   * Returns the price of an item given its index (unique identifier) 
   * an item
   * 
   * @param index - unique identifier of
   * @return price of Item
   */
  public static double getItemPrice(int index) {
    String price = GROCERY_ITEMS[index][1].replaceAll("[$]", ""); //Delete the dollar sign.
    double iPrice = Double.parseDouble(price);
    return iPrice;
  }

  /**
   * Prints the Catalog of the grocery store (item identifiers, names, and prices)
   * 
   * @param None
   * @return None
   */
  public static void printCatalog() {
    // Complete the missing code /* */ in the following implementation
    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
    System.out.println("Item id \tName \t Price");
    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
    for (int i = 0; i < GROCERY_ITEMS.length; i++) {
      System.out.println(i + "\t\t" + GROCERY_ITEMS[i][0] + " \t " + GROCERY_ITEMS[i][1]);
    }
    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
  }

  /**
   * Adds the name of a grocery item given its identifier at the end of the oversize array.
   * displays the message "Error! No additional item can be scanned. Please wait for assistance." if 
   * array is full.
   * 
   * @param id    - identifier of the item to be added to the bagging area (index of the item in the
   *              GROCERY_ITEMS array)
   * @param items - array storing the names of the items checked out and placed in the bagging area
   * @param size  - number of elements stored in items before trying to add a new item
   * @return the number of elements stored in bagging area after the item with the provided
   *         identifier was added to the bagging area
   */
  public static int addItemToBaggingArea(int id, String[] items, int size) {
    if (size == items.length) {
      System.out.println("Error! No additional item can be scanned. Please wait for assistance.");
      return size;
    } else {
      items[size] = GROCERY_ITEMS[id][0];
    }
    return size + 1;
  }

  /**
  * Returns the number of occurrences of a given item in an oversize array of
  * strings. 
  * @param item - item to count its occurrences
  * @param items - a bag of string items
  * @param size - number of items stored in items
  * @return the number of occurrences of a given item in an oversize array
   */
  public static int count(String item, String[] items, int size) {
    int count = 0;
    if (size == 0) {
      return -1;
    }
    for (int i = 0; i < size; ++i) {
      if (items[i].equalsIgnoreCase(item)) {
        count = count + 1;
      }
    }
    return count;
  }

/**
 * Returns the index of the first occurrence of item in items if found,
 * and -1 if the item not found
 * 
 * @param item - element to search for
 * @param items - an array of string elements
 * @param size - number of elements stored in items
 * @return the index of the first occurrence of item in items
 */
  public static int indexOf(String item, String[] items, int size) {
    if (size == 0) {
      return -1;
    }
    for (int i = 0; i < size; ++i) {
      if (items[i].equalsIgnoreCase(item)) {
        return i;
      }
    }
    return -1;
  }

/**
 * Removes the first occurrence of itemToRemove from the bagging area
 * defined by the array items and its size. If no match with
 * itemToRemove is found, the method displays the following error
 * message "WARNING: item not found." without making any change
 * to the items array. This method compacts the contents of the items
 * array after removing the itemToRemove so there are no empty spaces
 * in the middle of the array.
 * 
 * @param itemToRemove - item to remove from the bagging area
 * @param items - a bag of items
 * @param size - number of elements stored in the bag of items
 * @return returns the number of items present in the cart after the
 * itemToRemove is removed from the cart
 */
  public static int remove(String itemToRemove, String[] items, int size) {
    if (size == 0) {
      return -1;
    }
    for (int i = 0; i < size; ++i) {
      if (itemToRemove.equalsIgnoreCase(items[i])) {
        items[i] = null;
        for (int j = i; j < size - 1; ++j) {
          items[j] = items[j + 1]; // moves every item after remove one index forwards
        }
        items[size - 1] = null;

        size = size - 1;
        return size;
      }
    }
    return -1;
  }

  /**
   * Gets a copy of the items array without duplicates. Adds every unique item
   * stored within the items array to the itemsSet array.The itemsSet array is
   * initially empty. Recall that a set is a collection which does not contain
   * duplicate items).
   * On the other hand, this method does not make any change to the contents
   * of the items array.
   *  
   * @param items - list of items added to the bagging area
   * @param size - number of elements stored in items
   * @param itemsSet - reference to an empty array which is going to contain the set
   * of items checked out (it does not contain duplicates)
   * @return the number of elements in items without accounting duplicates.
   * 
   */
  public static int getUniqueCheckedOutItems(String[] items, int size, String[] itemsSet) {
    itemsSet[0] = items[0]; // copies first item to array
    int itemSetSize = 1;
    for (int i = 1; i < size; ++i) {
      String curritem = items[i];
      itemSetSize = compareToSet(curritem, itemsSet, itemSetSize);
    }
    return itemSetSize;
  }

/**
 * Helper method that checks whether or not an item is already present in itemsSet array. 
 * 
 * @param curritem - an array which stores the items checked out.
 * @param itemsSet - array which holds set of items at checkout.
 * @param itemSetSize - size of itemsSet array.
 * @return true if this winner equals the input provided object and false otherwise.
 */
 private static int compareToSet(String curritem, String[] itemsSet, int itemSetSize) { //private 
   //over public was recommended by TA
    for (int j = 0; j < itemSetSize; ++j) {

      if (curritem.equalsIgnoreCase(itemsSet[j])) {// don't add if element is already in itemsSet.
        // move to next element
        return itemSetSize; // Where I have my problem
      }
    }
    itemsSet[itemSetSize] = curritem; // add if element is not yet present in itemsSet.
    ++itemSetSize;    
    return itemSetSize;
  }

/**
 * Returns the total value (price) of the scanned items at checkout
 * without tax in $ (double)
 * 
 * @param items - an array which stores the items checked out
 * @param size - number of elements stored in the items array
 * @return the total value (price) of the scanned items at checkout
 */
  public static double getSubTotalPrice(String[] items, int size) {
    double tPrice=0.0;    
    for ( int i =0; i<size; ++i) {
      int itemIndex= getItemIndex(items[i]) ;
      double iPrice=getItemPrice(itemIndex);
      tPrice=tPrice + iPrice;
    }
    return tPrice;
  }
  
/**
 * Helper Method which returns the index of a given item passed as String.
 * 
 * @param name - name of item whose index is to be gotten
 * @return index of item in checkout basket.
 */
  private static int getItemIndex(String name) {
    for ( int i =0; i<GROCERY_ITEMS.length; ++i) {
      if (name.equalsIgnoreCase(GROCERY_ITEMS[i][0])) {
      return i;
    }
      }
    return 1;
  }
}
