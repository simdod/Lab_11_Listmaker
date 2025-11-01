import java.util.ArrayList;
import java.util.Scanner;

public class ListMaker {

    private static ArrayList<String> list = new ArrayList<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean done = false;

        do
        {
            displayList();
            String cmd = SafeInput.getRegExString(in,
                    "Choose [A]dd, [D]elete, [I]nsert, [P]rint, or [Q]uit: ",
                    "[AaDdIiPpQq]").toUpperCase();

            switch (cmd)
            {
                case "A":
                    addItem(in);
                    break;
                case "D":
                    deleteItem(in);
                    break;
                case "I":
                    insertItem(in);
                    break;
                case "P":
                    printList();
                    break;
                case "Q":
                    if (SafeInput.getYNConfirm(in, "Are you sure you want to quit?"))
                    {
                        done = true;
                    }
                    break;
            }
        } while (!done);

        System.out.println("\nThanks for using ListMaker! Goodbye!");
    }


    private static void displayList()
    {
        System.out.println("\n=======================");
        System.out.println("      Current List");
        System.out.println("=======================");
        if (list.isEmpty())
        {
            System.out.println("<< List is empty >>");
        } else
        {
            for (int i = 0; i < list.size(); i++)
            {
                System.out.printf("%3d: %s\n", i + 1, list.get(i));
            }
        }
        System.out.println("=======================");
    }

    private static void addItem(Scanner in)
    {
        String item = SafeInput.getNonZeroLenString(in, "Enter the item to add");
        list.add(item);
        System.out.println("Item added to the end of the list.");
    }

    private static void deleteItem(Scanner in) {
        if (list.isEmpty())
        {
            System.out.println("List is empty. Nothing to delete!");
            return;
        }

        printList();
        int choice = SafeInput.getRangedInt(in,
                "Enter the number of the item to delete: ",
                1, list.size());
        list.remove(choice - 1);
        System.out.println("Item deleted.");
    }

    private static void insertItem(Scanner in) {
        if (list.isEmpty()) {
            System.out.println("List is empty. Please add at least one item first.");
            return;
        }

        // show current list so user can choose a position
        printList();

        // get the item to insert
        String item = SafeInput.getNonZeroLenString(in, "Enter the item to insert");

        // get position: you can insert between 1 and list.size() + 1
        int position = SafeInput.getRangedInt(in,
                "Enter position to insert (1 = top, " + (list.size() + 1) + " = end): ",
                1, list.size() + 1);

        // insert the item into existing list (shifts the others down automatically)
        list.add(position - 1, item);

        System.out.println("Inserted \"" + item + "\" at position " + position + ".");
    }


    private static void printList()
    {
        System.out.println("\n======= LIST CONTENTS =======");
        if (list.isEmpty())
        {
            System.out.println("<< List is empty >>");
        } else
        {
            for (int i = 0; i < list.size(); i++)
            {
                System.out.printf("%3d: %s\n", i + 1, list.get(i));
            }
        }
        System.out.println("=============================\n");
    }
}
