package com.gnaw.gui.file;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import com.gnaw.models.SharedFile;

public class FileSystemModel implements TreeModel {
	private SharedFile root;

	private Vector listeners = new Vector();

	public FileSystemModel(SharedFile rootDirectory) {
		root = rootDirectory;
	}

	public Object getRoot() {
		return root;
	}

	public Object getChild(Object parent, int index) {
		SharedFile directory = (SharedFile) parent;
		return directory.getSharedFiles().get(index);
	}

	public int getChildCount(Object parent) {
		SharedFile file = (SharedFile) parent;
		if (file.isFolder()) {
			ArrayList<SharedFile> fileList = file.getSharedFiles();
			if (fileList != null)
				return file.getSharedFiles().size();
		}
		return 0;
	}

	public boolean isLeaf(Object node) {
		SharedFile file = (SharedFile) node;
		return !file.isFolder();
	}

	public int getIndexOfChild(Object parent, Object child) {
		SharedFile directory = (SharedFile) parent;
		SharedFile file = (SharedFile) child;
		ArrayList<SharedFile> children = directory.getSharedFiles();
		for (int i = 0; i < children.size(); i++) {
			if (file.getName().equals(children.get(i).getName())) {
				return i;
			}
		}
		return -1;

	}

	public void valueForPathChanged(TreePath path, Object value) {
/*		SharedFile oldFile = (SharedFile) path.getLastPathComponent();
		SharedFile targetFile = oldFile.getParent();
		String newFileName = (String) value;
		oldFile.renameTo(targetFile);
		File parent = new File(fileParentPath);
		int[] changedChildrenIndices = { getIndexOfChild(parent, targetFile) };
		Object[] changedChildren = { targetFile };
		fireTreeNodesChanged(path.getParentPath(), changedChildrenIndices,
				changedChildren);
*/
	}

	private void fireTreeNodesChanged(TreePath parentPath, int[] indices,
			Object[] children) {
		TreeModelEvent event = new TreeModelEvent(this, parentPath, indices,
				children);
		Iterator iterator = listeners.iterator();
		TreeModelListener listener = null;
		while (iterator.hasNext()) {
			listener = (TreeModelListener) iterator.next();
			listener.treeNodesChanged(event);
		}
	}

	public void addTreeModelListener(TreeModelListener listener) {
		listeners.add(listener);
	}

	public void removeTreeModelListener(TreeModelListener listener) {
		listeners.remove(listener);
	}
}