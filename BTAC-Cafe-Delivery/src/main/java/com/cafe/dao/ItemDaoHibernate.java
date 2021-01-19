package com.cafe.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cafe.models.Items;
import com.cafe.util.HibernateUtil;

public class ItemDaoHibernate implements ItemDao {
	private final static Logger Log = Logger.getLogger(ItemDaoHibernate.class);

	@Override
	public void addItem(Items item) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		ses.save(item);
		tx.commit();
		ses.close();
	}

	@Override
	public Items getItem(int id) {
		// TODO Auto-generated method stub
		Session ses = HibernateUtil.getSession();
		Items item = ses.get(Items.class,  id);
		ses.close();
		return item;
	}

	@Override
	public Items getItemByName(String name) {
		Session ses = HibernateUtil.getSession();
		Items item = ses.createQuery("FROM Order WHERE itemName = :name", Items.class)
				.setParameter("name", name).list().get(0);
		return item;
	}

	@Override
	public List<Items> getItems() {
		Session ses = HibernateUtil.getSession();
		List<Items> items = ses.createQuery("FROM Items", Items.class).list();
		return items;
	}

	@Override
	public void removeItem(Items item) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		ses.delete(item);
		tx.commit();
		ses.close();
	}

}
