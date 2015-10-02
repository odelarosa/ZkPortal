package com.delarosa.portal.zk;

import java.util.Collection;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Toolbarbutton;

/**
 *
 * @author odelarosa
 */
public abstract class SearchWindow extends Window {

    private final ListModelList<Object> model = new ListModelList<>();

    public SearchWindow(boolean addToolBar) {
        super(addToolBar);
        
        Toolbarbutton toolbarbutton = new Toolbarbutton(null);
        toolbarbutton.setIconSclass("z-icon-refresh fa-2x");
        
        toolbarbutton.addEventListener(Events.ON_CLICK, (Event t) -> {
            refresh();
        });
        
        getToolbar().appendChild(toolbarbutton);

        getPanelLayout().newPanelChildren(getSearchTitle(), true, getSearchPanel());
        getPanelLayout().newPanelChildren(getResultTitle(), true, getResultPanel());

        getPanelLayout().newEmptyDiv();
        
        refresh();
        
        setWidth("100%");
        setHeight("100%");
    }

    public String getSearchTitle() {
        return "Búsqueda";
    }

    public String getResultTitle() {
        return "Resultado";
    }

    private Listbox getResultPanel() {
        Listbox listbox = new Listbox();
        listbox.setMold("paging");
        listbox.setPageSize(15);
        listbox.setEmptyMessage("No hay resultados");
        listbox.setModel(getModel());
        listbox.setItemRenderer(getItemRenderer());
        listbox.appendChild(getListHeader());

        return listbox;
    }

    public abstract Component getSearchPanel();

    public abstract ListitemRenderer<?> getItemRenderer();

    private ListModelList<Object> getModel() {
        return model;
    }

    public abstract Listhead getListHeader();

    public final void refresh() {
        getModel().clear();
        getModel().addAll(getResults());
    }

    public abstract Collection<?> getResults();
}
