package ua.lviv.iot.view;

import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


public class View {
    private final Scanner input = new Scanner(System.in);
    private final HashMap<String, ViewAction> optionsMap = new HashMap<>();
    private final ViewOptions viewOptions;
    @Setter
    private boolean canExit;

    public View() {
        viewOptions = new ViewOptions();
        setViewOptions();
        canExit = false;
    }

    public void view() {
        viewOptions.printHelp(new ArrayList<>());
        while (true) {
            String actionString = input.nextLine();
            List<String> actionParams = new ArrayList<>(Arrays.asList(actionString.split(" ")));
            String action = actionParams.get(0);
            actionParams.remove(0);

            if (optionsMap.containsKey(action)) {
                optionsMap.get(action).execute(actionParams);
            } else {
                System.out.printf(
                        "Unknown command '%s'. Please, call 'h' or 'help' to see available options%n", action
                );
            }

            if (canExit) {
                System.out.println("Good bye!");
                return;
            }
        }
    }

    private interface ViewAction {
        void execute(List<String> params);
    }

    private void setViewOptions()  {
        optionsMap.put("h", viewOptions::printHelp);
        optionsMap.put("help", viewOptions::printHelp);
        optionsMap.put("show-tables", viewOptions::printAvailableTables);
        optionsMap.put("current-table", viewOptions::printCurrentTable);
        optionsMap.put("choose-table", viewOptions::chooseTable);
        optionsMap.put("describe", viewOptions::describeTable);
        optionsMap.put("get-all", viewOptions::getAllContent);
        optionsMap.put("get", viewOptions::getById);
        optionsMap.put("create", viewOptions::create);
        optionsMap.put("update", viewOptions::update);
        optionsMap.put("delete", viewOptions::delete);
        optionsMap.put("q", (params) -> viewOptions.declareExit(this));
        optionsMap.put("quit", (params) -> viewOptions.declareExit(this));
    }
}
