package twentytwentythree.day19;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Pattern;

public class Day19 {
    private sealed interface Rule {}

    private sealed interface Outcome {}

    private record GotoOutcome(String workFlow) implements Outcome {}
    private record AcceptOutcome() implements Outcome {}
    private record RejectOutcome() implements Outcome {}

    private static record Less(char category, int value, Outcome outcome) implements Rule {}
    private static record Greater(char category, int value, Outcome outcome) implements Rule {}
    private static record Goto(String workFlow) implements Rule {}
    private static record Accept(AcceptOutcome accept) implements Rule {}
    private static record Reject(RejectOutcome reject) implements Rule {}

    public static int solvePartOne(File file) throws FileNotFoundException {
        var scanner = new Scanner(file);
        var workFlowMap = scanWorkFlows(scanner);

        // Pass values to workflows
        var total = 0;
        while (scanner.hasNextLine()) {
            var line = scanner.nextLine();
            var valMap = new HashMap<Character, Integer>();
            var xMatch = Pattern.compile("x=(\\d+)").matcher(line);
            xMatch.find();
            var x = Integer.parseInt(xMatch.group(1));
            valMap.put('x', x);
            var mMatch = Pattern.compile("m=(\\d+)").matcher(line);
            mMatch.find();
            var m = Integer.parseInt(mMatch.group(1));
            valMap.put('m', m);
            var aMatch = Pattern.compile("a=(\\d+)").matcher(line);
            aMatch.find();
            var a = Integer.parseInt(aMatch.group(1));
            valMap.put('a', a);
            var sMatch = Pattern.compile("s=(\\d+)").matcher(line);
            sMatch.find();
            var s = Integer.parseInt(sMatch.group(1));
            valMap.put('s', s);

            var workFlow = workFlowMap.get("in");
            Outcome result = null;
            while (result == null) {
                for (var i = 0; i < workFlow.size(); ++i) {
                    var exitWorkflow = false;
                    var foundResult = false;
                    switch (workFlow.get(i)) {
                        case Less(var category, var value, var outcome) -> {
                            if (valMap.get(category) < value) {
                                switch (outcome) {
                                    case AcceptOutcome acceptOutcome -> {
                                        result = acceptOutcome;
                                        foundResult = true;
                                    }
                                    case RejectOutcome rejectOutcome -> {
                                        result = rejectOutcome;
                                        foundResult = true;
                                    }
                                    case GotoOutcome(var newWorkFlow) -> {
                                        workFlow = workFlowMap.get(newWorkFlow);
                                        exitWorkflow = true;
                                    }
                                }
                            } else {
                                continue;
                            }
                        }
                        case Greater(var category, var value, var outcome) -> {
                            if (valMap.get(category) > value) {
                                switch (outcome) {
                                    case AcceptOutcome acceptOutcome -> {
                                        result = acceptOutcome;
                                        foundResult = true;
                                    }
                                    case RejectOutcome rejectOutcome -> {
                                        result = rejectOutcome;
                                        foundResult = true;
                                    }
                                    case GotoOutcome(var newWorkFlow) -> {
                                        workFlow = workFlowMap.get(newWorkFlow);
                                        exitWorkflow = true;
                                    }
                                }
                            } else {
                                continue;
                            }
                        }
                        case Goto(var newWorkFlow) -> {
                            workFlow = workFlowMap.get(newWorkFlow);
                            exitWorkflow = true;
                        }
                        case Accept(var outcome) -> {
                            result = outcome;
                            foundResult = true;
                        }
                        case Reject(var outcome) -> {
                            result = outcome;
                            foundResult = true;
                        }
                    }
                    if (foundResult || exitWorkflow) break;
                }
            }

            if (result instanceof AcceptOutcome) {
                total += valMap.get('x');
                total += valMap.get('m');
                total += valMap.get('a');
                total += valMap.get('s');
            }
        }

        return total;
    }

    public static int solvePartTwo(File file) throws FileNotFoundException {
        var scanner = new Scanner(file);
        var workFlowMap = scanWorkFlows(scanner);

        // do a search through the workflow graph. Keep track of possible min and max for each value in a traversal.
        // on acceptance, add the min/max of each value to a list for each value's min/maxes
        // calculate possible ranges (handle for overlaps in mins/maxes)
        // using ranges, get total combinations
        return 0;
    }

    private static Map<String, List<Rule>> scanWorkFlows(Scanner scanner) {
        var workFlowMap = new HashMap<String, List<Rule>>();
        // Load in workflows
        while (scanner.hasNextLine()) {
            var line = scanner.nextLine();
            if (line.isEmpty()) break;
            var nameMatcher = Pattern.compile("^([^{]*)\\{").matcher(line);
            nameMatcher.find();
            var name = nameMatcher.group(1);
            var ruleStrings = line.substring(line.indexOf('{') + 1, line.length() - 1).split(",");
            var rules = new ArrayList<Rule>();
            for (var s : ruleStrings) {
                if (s.contains(":")) {
                    var checkS = s.split(":")[0];
                    var outcomeS = s.split(":")[1];

                    Outcome outcome;
                    if (outcomeS.equals("A")) outcome = new AcceptOutcome();
                    else if (outcomeS.equals("R")) outcome = new RejectOutcome();
                    else outcome = new GotoOutcome(outcomeS);

                    Rule rule;
                    if (checkS.contains("<")) rule = new Less(checkS.split("<")[0].charAt(0), Integer.parseInt(checkS.split("<")[1]), outcome);
                    else rule = new Greater(checkS.split(">")[0].charAt(0), Integer.parseInt(checkS.split(">")[1]), outcome);

                    rules.add(rule);
                } else if (s.equals("A")) {
                    rules.add(new Accept(new AcceptOutcome()));
                } else if (s.equals("R")) {
                    rules.add(new Reject(new RejectOutcome()));
                } else {
                    rules.add(new Goto(s));
                }
            }
            workFlowMap.put(name, rules);
        }
        return workFlowMap;
    }
}
