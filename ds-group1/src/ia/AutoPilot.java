package ia;

import java.awt.List;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

import map.Cell;
import map.Diamond;
import map.Earth;
import map.Empty;
import map.Map;

public class AutoPilot {
	
	private Map map;
	
	public AutoPilot(Map map){
		this.map = map;
	}
	
	private boolean isWalkable(Cell cell){
		return (cell instanceof Empty || cell instanceof Earth || cell instanceof Diamond);
	}
	
	/**
	 * Get the best path between the Robot (origin) and a list of destinations (destination is chosen by minDistance)
	 * 
	 * @param origin
	 * @param destinations
	 * @return List of points (path)
	 */
	public LinkedList<Point> getPath(Point origin, LinkedList<Point> destinations){
		
		ArrayList<Double> tempPoints = new ArrayList<Double>(destinations.size());
		for (Point point : destinations) {
			tempPoints.add(point.distance(origin));
		}
		
		double minDistance = Collections.min(tempPoints);
		ArrayList<Integer> nearPointsIndex = new ArrayList<Integer>();

		for(int i = 0; i < tempPoints.size(); i++){
			if(tempPoints.get(i) == minDistance){
				nearPointsIndex.add(i);
			}
		}
		
		Random r = new Random();
		int minDistancePointIndex = nearPointsIndex.get(r.nextInt(nearPointsIndex.size()));
		Point destination = destinations.get(minDistancePointIndex);
		
		return getPath(origin, destination);
	}
	
	/**
	 * Calculates a path between 2 points.
	 * Avoids obstacles.
	 * @param origin
	 * @param destination
	 * @return list of points
	 */
	public LinkedList<Point> getPath(Point origin, Point destination) {
		LinkedList<Point> path = new LinkedList<Point>();
		
		/*
		 * Open and closed points list
		 */
		LinkedList<Point> openPoints = new LinkedList<Point>();
		LinkedList<Point> closedPoints = new LinkedList<Point>();
		
		/*
		 * Add to openPoints origin point 
		 */
		openPoints.add(origin);
		
		/*
		 * Get the path from origin to destination
		 */
		//System.out.println("origin: " + origin + " - destination: " + destination);
		path = this.getPathAux(openPoints, closedPoints, origin, destination);
		
		/* Print */
		/*for (Point point : path) {
			System.out.println("PATH ("+origin.x+","+origin.y+":"+destination.x+","+destination.y+"): " + point.getX() + ":" + point.getY());
		}*/
		
		if (path.size() >= 2) {
			path.pop();
		}
		
		return path;
	}
	
	/**
	 * 
	 * @param openPoints
	 * @param closedPoints
	 * @param origin
	 * @param destination
	 * @return A list of ordered points to follow
	 */
	private LinkedList<Point> getPathAux(LinkedList<Point> openPoints, LinkedList<Point> closedPoints, Point origin, Point destination) {
		
		/*
		 * Search for the nearest point on openPoints
		 */
		Point currentPoint = (Point) origin.clone();
		//System.out.println("currentPoint.distance(destination) = " + currentPoint.distance(destination));
		while(currentPoint.distance(destination) != 0){
			
			if (openPoints.isEmpty()) {
				//System.out.println("openPoints.isEmpty");
				return new LinkedList<Point>();
			}
			
			Point minDistancePoint;
			
			if(openPoints.size() == 1) {
				minDistancePoint = openPoints.pop();
			}else {
				ArrayList<Double> tempPoints = new ArrayList<Double>(openPoints.size());
				for (Point point : openPoints) {
					tempPoints.add(point.distance(destination));
					//tempPoints.add(MathUtils.calcWorldDistance(getSpace().getSizeX(), getSpace().getSizeY(), point, destination));
				}
				
				double minDistance = Collections.min(tempPoints);
				ArrayList<Integer> nearPointsIndex = new ArrayList<Integer>();

				for(int i = 0; i < tempPoints.size(); i++){
					if(tempPoints.get(i) == minDistance){
						nearPointsIndex.add(i);
					}
				}
				
				Random r = new Random();
				int minDistancePointIndex = nearPointsIndex.get(r.nextInt(nearPointsIndex.size()));
				minDistancePoint = openPoints.get(minDistancePointIndex);
				openPoints.remove(minDistancePointIndex);
			}
			
			closedPoints.add(minDistancePoint);
			
			openPoints.clear();

			
			Cell left, down, up, right = null; 
			try{
				left = map.getXY(origin.x - 1, origin.y);
				left = isWalkable(left) ? left : null;
			}catch(IndexOutOfBoundsException e){
				left = null;
			}
			
			try{
				up = map.getXY(origin.x, origin.y + 1);
				up = isWalkable(up) ? up : null;
			}catch(IndexOutOfBoundsException e){
				up = null;
			}
			
			try{
				down = map.getXY(origin.x, origin.y - 1);
				down = isWalkable(down) ? down : null;
			}catch(IndexOutOfBoundsException e){
				down = null;
			}
			
			try{
				right = map.getXY(origin.x + 1, origin.y);
				right = isWalkable(right) ? right : null;
			}catch(IndexOutOfBoundsException e){
				right = null;
			}
			
			Point leftPoint = new Point(origin.x - 1, origin.y);
			if(left != null && !closedPoints.contains(leftPoint) && !openPoints.contains(leftPoint)){
				openPoints.add(leftPoint);
			}
			
			Point upPoint = new Point(origin.x, origin.y + 1);
			if(up != null && !closedPoints.contains(upPoint) && !openPoints.contains(upPoint)){
				openPoints.add(upPoint);
			}
			
			Point downPoint = new Point(origin.x, origin.y - 1);
			if(down != null && !closedPoints.contains(downPoint) && !openPoints.contains(downPoint)){
				openPoints.add(downPoint);
			}
			
			Point rightPoint = new Point(origin.x + 1, origin.y);
			if(right != null && !closedPoints.contains(rightPoint) && !openPoints.contains(rightPoint)){
				openPoints.add(rightPoint);
			}
			
			currentPoint = minDistancePoint;
		}
		
		return closedPoints;
		
	}

}
