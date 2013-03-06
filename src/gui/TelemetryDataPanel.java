/**
 * 
 */
package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data.DataCell;
import data.DataSet;

/**
 * @author kamil
 *
 */
public class TelemetryDataPanel extends JPanel {

	DataSet dataSet;
	ArrayList<DataCellPanel> cells = new ArrayList<DataCellPanel>();
	
	
	/**
	 * 
	 */
	public TelemetryDataPanel(int rows, int cols, DataSet dataSet) {
		super(new BorderLayout());
		this.dataSet = dataSet;
		JPanel panel = new JPanel();	
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	
		for(DataCell cell : dataSet.getDataSet()){
			cells.add(new DataCellPanel(cell));
		}
		
		for(DataCellPanel cell : cells){
			panel.add(cell);
		}
		
		this.add(panel, BorderLayout.NORTH);
		this.setVisible(true);
		
		this.getCellPanelById("lat").changeValue(40);
		
	}
	
	
	public ArrayList<DataCellPanel> getCells() {
		return cells;
	}
	public void setCells(ArrayList<DataCellPanel> cells) {
		this.cells = cells;
	}

	public DataCellPanel getCellPanelById(String id){
		for(DataCellPanel cp : cells){
			if(cp.getId().equals(id)) return cp;
		}
		return null;
	}
	
	/**
	 * Updates all data labels with values from dataSet
	 */
	public void updateCells(){
		for(DataCellPanel cell : cells){
			/*
			 * That really is awful, but basically it gets value from the dataSet
			 * (by the id of the DataCellPanel) and changes the label of the cell.
			 */
			cell.changeValue(dataSet.getValueByKey(cell.getId()));
		}
		this.invalidate();
		this.repaint();
	}

	/**
	 * Allows to choose which data will not be displayed on the panel.
	 * @param id
	 */
	public void stopDisplaying(String id){
		for(int i = 0; i<this.cells.size(); i++){
			if(this.cells.get(i).getId().equals(id)){
				//this.remove(this.cells.get(i));
				this.validate();	
				this.cells.remove(i);
				return;
			}
				
		}
		System.out.print("Cannot stop displaying "+id+". Such cell does not exist.");
	}

}
