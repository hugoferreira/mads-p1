package main;

import java.util.Stack;

public class MapTimeHandler {

public Stack<Map> past = new Stack<Map>();
public Stack<Map> future = new Stack<Map>();


public boolean undo()
{
return future.add(past.pop());
}

public boolean redo()
{
if(!canRedo())
return false;

return past.add(future.pop());
}

public boolean canRedo()
{
return future.size() > 0;
}

public void storeMap(Map m)
{
	past.add(m);
	future.clear();
	}
}