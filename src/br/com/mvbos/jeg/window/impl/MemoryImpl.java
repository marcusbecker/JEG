package br.com.mvbos.jeg.window.impl;

import br.com.mvbos.jeg.element.ElementModel;
import br.com.mvbos.jeg.window.IMemory;

/**
 * 
 * @author Marcus Becker
 */
public class MemoryImpl implements IMemory {

	private final ElementModel[] elementList;
	private int elementCount;
	private ElementModel background;

	public MemoryImpl() {
		elementList = new ElementModel[200];
	}

	public MemoryImpl(int size) {
		elementList = new ElementModel[size];
	}

	@Override
	public void registerElement(ElementModel e) {
		if (e != null && elementCount < elementList.length) {
			elementList[elementCount] = e;
			elementCount++;
		} else {
			System.out.println("Out of memory");
		}
	}

	@Override
	public void setBackGrountElement(ElementModel e) {
		background = e;
	}

	@Override
	public ElementModel getBackground() {
		return background;
	}

	@Override
	public ElementModel[] getElementList() {
		return elementList;
	}

	@Override
	public ElementModel getByElement(int i) {
		if (i < elementCount) {
			return elementList[i];
		}

		return null;
	}

	@Override
	public int getElementCount() {
		return elementCount;
	}

	@Override
	public void clear() {
		for (int i = 0; i < elementCount; i++) {
			elementList[i] = null;
		}

		elementCount = 0;
	}

	@Override
	public ElementModel findElementById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ElementModel findElementByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void unRegisterElement(ElementModel e) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getCapacity() {
		return elementList.length;
	}
}
