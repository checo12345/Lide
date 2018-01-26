package com.lidebeta.spi;

import static com.lidebeta.spi.service.OfyService.ofy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import java.net.URL;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import com.google.common.io.CharStreams;

import com.google.api.server.spi.auth.common.User;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.response.UnauthorizedException;
import com.google.appengine.api.search.Cursor;
import com.google.appengine.api.search.IndexSpec;
import com.google.appengine.api.search.Query;
import com.google.appengine.api.search.QueryOptions;
import com.google.appengine.api.search.Results;
import com.google.appengine.api.search.ScoredDocument;
import com.google.appengine.api.search.SearchServiceFactory;
import com.googlecode.objectify.Key;
import com.lidebeta.spi.bean.Admin;
import com.lidebeta.spi.bean.AdminPack;
import com.lidebeta.spi.bean.CoverageArea;
import com.lidebeta.spi.bean.DeliveryMan;
import com.lidebeta.spi.bean.Number;
import com.lidebeta.spi.bean.Keyword;
import com.lidebeta.spi.bean.Keywords;
import com.lidebeta.spi.bean.Order;
import com.lidebeta.spi.bean.Product;
import com.lidebeta.spi.bean.Store;
import com.lidebeta.spi.bean.Sync;
import com.lidebeta.spi.dao.AdminDAO;
import com.lidebeta.spi.dao.AdminDAOImpl;

@Api(	name = "adminApi", 
		version = "v1", 
		scopes = { 
				Constants.EMAIL_SCOPE 
				}, 
		clientIds = {
				Constants.WEB_CLIENT_ID, 
				Constants.API_EXPLORER_CLIENT_ID,
				Constants.ANDROID_MANAGER_CLIENT_ID,
				"347485897175-kf18ekkqs2d1t30r25u4sdqogts17gv9.apps.googleusercontent.com"}, 
		audiences = {Constants.ANDROID_AUDIENCE},
		description = "API for admin products."
	)
public class AdminApi {
	
	private static final Logger log = Logger.getLogger(AdminApi.class.getName());
	private static final AdminDAO ADMIN_DAO = new AdminDAOImpl();
	
	@ApiMethod(name="updateProduct", path="updateProduct", httpMethod=HttpMethod.POST)
	public Product updateProduct(User user, Product product) throws UnauthorizedException{
		
		if(user == null){
    		throw new UnauthorizedException("Authoruzation required user = " + user);
    	}
		
		Key<Admin> adminKey = Key.create(Admin.class, user.getEmail());
		Admin admin = ofy().load().key(adminKey).now();
		
		if(		 admin==null || 
				!admin.getCoverageAreaIdList().	contains(product.getCoverageAreaId()) || 
				!admin.getStoreIdList().		contains(product.getStoreId()) ||
				!admin.isActive()){
			throw new UnauthorizedException("Authentication fail admin = " + String.valueOf(admin));
		}
		
		return ADMIN_DAO.updateProduct(product);

	}
	
	@ApiMethod(name="fetchProductsByKeyword", path="fetchProductsByKeyword", httpMethod=HttpMethod.POST)
	public List<Product> fetchProductsByKeyword(User user, Keyword keyword) throws UnauthorizedException{
		
		if(user == null){
    		throw new UnauthorizedException("Authoruzation required");
    	}
		
		Key<Admin> adminKey = Key.create(Admin.class, user.getEmail());
		Admin admin = ofy().load().key(adminKey).now();
		
		if(		 admin==null || 
				!admin.getCoverageAreaIdList().	contains(keyword.getCoverage_area_id()) || 
				!admin.getStoreIdList().		contains(keyword.getStore_id()) ||
				!admin.isActive()){
			throw new UnauthorizedException("Authentication fail");
		}
		
		return ADMIN_DAO.fetchProductsByKeyword(keyword);
		
	}

	@ApiMethod(name="fetchProductByCb", path="fetchProductByCb", httpMethod=HttpMethod.POST)
	public Product fetchProductByCb(Product product) throws UnauthorizedException{
		/*
		if(user == null){
    		throw new UnauthorizedException("Authoruzation required");
    	}
		
		Key<Admin> adminKey = Key.create(Admin.class, user.getEmail());
		Admin admin = ofy().load().key(adminKey).now();
		
		if(		 admin==null || 
				!admin.getCoverageAreaIdList().contains(product.getCoverageAreaId()) ||
				!admin.isActive()){
			throw new UnauthorizedException("Authentication fail");
		}
		*/
		return ADMIN_DAO.fetchProductByCb(product);
		
	}
	
	@ApiMethod(name="updateStore", path="updateStore", httpMethod=HttpMethod.POST)
	public Store updateStore(User user, Store store) throws UnauthorizedException{
		
		if(user == null){
    		throw new UnauthorizedException("Authoruzation required");
    	}
		
		Key<Admin> adminKey = Key.create(Admin.class, user.getEmail());
		Admin admin = ofy().load().key(adminKey).now();
		
		if(		 admin==null || 
				!admin.getCoverageAreaIdList().	contains(store.getCoverageAreaId())|| 
				!admin.getStoreIdList().		contains(store.getId()) ||
				!admin.isActive()){
			throw new UnauthorizedException("Authentication fail");
		}
		
		return ADMIN_DAO.updateStore(store);
		
	}

	@ApiMethod(name="updateStoreStatus", path="updateStoreStatus", httpMethod=HttpMethod.POST)
	public Store updateStoreStatus(User user, Store store) throws UnauthorizedException{
		
		if(user == null){
    		throw new UnauthorizedException("Authoruzation required");
    	}
		
		Key<Admin> adminKey = Key.create(Admin.class, user.getEmail());
		Admin admin = ofy().load().key(adminKey).now();
		
		if(		 admin==null || 
				!admin.getCoverageAreaIdList().	contains(store.getCoverageAreaId())|| 
				!admin.getStoreIdList().		contains(store.getId()) ||
				!admin.isActive()){
			throw new UnauthorizedException("Authentication fail");
		}
		
		Key<CoverageArea> coverageAreaKey = Key.create(CoverageArea.class, store.getCoverageAreaId());
		CoverageArea coverageArea = ofy().load().key(coverageAreaKey).now();
		
		if(coverageArea==null){
			throw new IllegalArgumentException("Coverage Area with id="+store.getCoverageAreaId()+" does not exist");
		}
		
		Key<Store> storeKey = Key.create(coverageAreaKey, Store.class, store.getId());
		Store _store = ofy().load().key(storeKey).now();
		
		if(_store==null){
			throw new IllegalArgumentException("Store with id="+store.getId()+" does not exist");
		}
		
		_store.setOpen(store.isOpen());
		
		storeKey = ofy().save().entity(_store).now();
		return storeKey!=null?_store:null;
		
	}
	
	@ApiMethod(name="fetchOrder", path="fetchOrder", httpMethod=HttpMethod.POST)
	public List<Order> fetchOrder(User user, Sync sync) throws UnauthorizedException{
		
		if(user == null){
    		throw new UnauthorizedException("Authoruzation required null user");
    	}
		
		Key<Admin> adminKey = Key.create(Admin.class, user.getEmail());
		Admin admin = ofy().load().key(adminKey).now();
		
		
		
		if(		 admin==null || 
				!admin.getCoverageAreaIdList().	contains(sync.getCoverageAreaId())|| 
//				!admin.getStoreIdList().		contains(store.getId()) ||
				!admin.isActive()){
			throw new UnauthorizedException("Acces denied");
		}
		
		if(sync == null ){
			throw new IllegalArgumentException("store and store.sync are required");
		}else{
			if(sync.getFromDate()==null && sync.getOrders() == null){
				throw new IllegalArgumentException("fromDate or products required");
			}
		}
		
    	return ADMIN_DAO.getOrders(sync);
	}
	
	@ApiMethod(name="authenticate", path="authenticate", httpMethod=HttpMethod.POST)
	public AdminPack authenticate(User user, Admin _admin) throws UnauthorizedException{
		
		
		if(user == null){
    		throw new UnauthorizedException("Authoruzation required null user");
    	}
		
		Key<Admin> adminKey = Key.create(Admin.class, user.getEmail());
		Admin admin = ofy().load().key(adminKey).now();
	
		
		if(admin==null || !admin.isActive()){ 
			throw new UnauthorizedException("Acces denied");
		}
	
		admin.setMessengerToken(_admin.getMessengerToken());
		ofy().save().entity(admin).now();
		
		List<Store> stores = new ArrayList<>();
		List<CoverageArea> coverageAreaList = new ArrayList<>();
		for(CoverageArea coverageArea: admin.getCoverageAreaList()){
			for(Store store: coverageArea.getStroList()){
				Key<CoverageArea> coverageAreaKey = Key.create(CoverageArea.class, coverageArea.getId());
				store.setCoverageAreaKey(coverageAreaKey);
			}
			stores.addAll(coverageArea.getStroList());
		}
		
		Collection<CoverageArea> cCoverageArea = ofy().load().entities(admin.getCoverageAreaList()).values();
		Collection<Store> cStores = ofy().load().entities(stores).values();
		
		coverageAreaList.addAll(cCoverageArea);
		
		stores.clear();
		stores.addAll(cStores);
		
		for(Store store: stores){
			store.setCoverageAreaId(store.getCoverageAreaKey().getId());
		}
		
		AdminPack adminPack = new AdminPack();
		adminPack.setCoverageArea(coverageAreaList);
		adminPack.setStores(stores);
		return adminPack;
	}

	@ApiMethod(name="insertProducts", path="insertProducts", httpMethod=HttpMethod.POST)
	public void insertProducts(User user) throws UnauthorizedException{
		
		if(user == null){
    		throw new UnauthorizedException("Authoruzation required");
    	}
		
		Key<Admin> adminKey = Key.create(Admin.class, user.getEmail());
		Admin admin = ofy().load().key(adminKey).now();
		
		if(		 admin==null || 
				!admin.isActive()){
			throw new UnauthorizedException("Authentication fail");
		}
		
		InputStream resourceStream;
		try {
			resourceStream = new FileInputStream("WEB-INF/productos.csv");
			InputStreamReader inReader = new InputStreamReader(resourceStream, "UTF-8");
			BufferedReader br = new BufferedReader(inReader);
			String line;
			int i = 0;
			List<Product> products = new ArrayList();
			br.readLine();//read headers and get it out the way
			while ((line = br.readLine()) != null) {
				String[] columns = line.split(",");
				if(columns.length==5){
										
					Product product = new Product();
					
					product.setName(columns[1]);
					product.setImage("products/"+columns[2]);
					product.setKeywords(columns[3]);
					product.setDescription(columns[1]);
					
					double price = Double.valueOf(columns[4]) + 0.1*Double.valueOf(columns[4]);
					
					product.setPrice(price);
					
					product.setAvaible(true);
					
					product.setQuantity(10);
					
					product.setCoverageAreaId(4785074604081152l);
					product.setStoreId(5910974510923776l);
					
					product.setCodigoBarras(String.valueOf(i++));
					
					ADMIN_DAO.updateProduct(product);
					
				}
	        }
		} catch (FileNotFoundException e) {
			System.out.println("-----------------------fuck0");
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			System.out.println("-----------------------fuck1");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("-----------------------fuck2");
			e.printStackTrace();
		}
		
	}
	
	@ApiMethod(name="deleteProducts", path="deleteProducts", httpMethod=HttpMethod.POST)
	public Number deleteProducts(User user) throws UnauthorizedException{
		
		if(user == null){
    		throw new UnauthorizedException("Authoruzation required");
    	}
		
		Key<Admin> adminKey = Key.create(Admin.class, user.getEmail());
		Admin admin = ofy().load().key(adminKey).now();
		
		if(		 admin==null || 
				!admin.isActive()){
			throw new UnauthorizedException("Authentication fail");
		}
		
		
		Number integer = new Number();
		
		IndexSpec indexSpec = IndexSpec.newBuilder().setName("5629499534213120").build();
	    com.google.appengine.api.search.Index index = SearchServiceFactory.getSearchService().getIndex(indexSpec);
	    
	    
//	    Results<ScoredDocument> results = index.search("");
	    

	    
	    
	    List<String> ids = new ArrayList();
	    List<Product> products = new ArrayList<Product>();
//	    Cursor cursor = Cursor.newBuilder().build();
	    Results<ScoredDocument> result;
	    do {
	        // build options and query
//	        QueryOptions options = QueryOptions.newBuilder()
//	            .setCursor(cursor)
//	            .build();
//	        Query query = Query.newBuilder().setOptions(options).build("");
	    
	        // search at least once
	        result = index.search("");
	        System.out.println("------"+result.getNumberFound() + "---" + result.getNumberReturned());
	        integer.setValue(result.getNumberFound());
	        int numberRetrieved = result.getNumberReturned();
//	        cursor = result.getCursor();

	        if (numberRetrieved > 0) {
	        	for (ScoredDocument document : result) {
	    			ids.add(document.getId().toString());
	    			Product product = new Product();
	          	  	product.setCoverageAreaId	(Long.parseLong(document.getOnlyField(ProductContract.COLUMN_COVERAGE_AREA_ID).getAtom()));
	          	  	product.setStoreId			(Long.parseLong(document.getOnlyField(ProductContract.COLUMN_STORE_ID).getAtom()));
	    	  		product.setId				(document.getId()); 
	    	  		product.setName				(document.getOnlyField(ProductContract.COLUMN_NAME).getText()); 
	    	  		product.setDescription		(document.getOnlyField(ProductContract.COLUMN_DESCRIPTION).getText()); 
	    	  		product.setAvaible			(document.getOnlyField(ProductContract.COLUMN_AVAIBLE).getNumber()==1);
	    	  		product.setPrice			(document.getOnlyField(ProductContract.COLUMN_PRICE).getNumber()); 
	    	  		product.setImage			(document.getOnlyField(ProductContract.COLUMN_IMAGE).getAtom());
	    	  		product.setKeywords			(document.getOnlyField(ProductContract.COLUMN_KEYWORDS).getText());
	    	  		product.setCategories		(document.getOnlyField(ProductContract.COLUMN_CATEGORIES).getText());
	    	  		product.setQuantity  		(document.getOnlyField(ProductContract.COLUMN_QUANTITY).getNumber().intValue());
	    	      	products.add(product);
	    		}
	        	index.delete(ids);
	        }
	        
	    }
	    while (result.getNumberFound() > 0);
	    
	   return integer;
		
	    
	}
	
	@ApiMethod(name="setKeywordList", path="setKeywordList", httpMethod=HttpMethod.POST)
	public void setKeywordList(User user) throws UnauthorizedException{
		
		if(user == null){
    		throw new UnauthorizedException("Authoruzation required");
    	}
		
		Key<Admin> adminKey = Key.create(Admin.class, user.getEmail());
		Admin admin = ofy().load().key(adminKey).now();
		
		if(		 admin==null || 
				!admin.isActive()){
			throw new UnauthorizedException("Authentication fail");
		}
		
		
		Number integer = new Number();
		
		IndexSpec indexSpec = IndexSpec.newBuilder().setName("4785074604081152").build();
	    com.google.appengine.api.search.Index index = SearchServiceFactory.getSearchService().getIndex(indexSpec);
	    
	    
//	    Results<ScoredDocument> results = index.search("");
	    

	    
	    
	    List<String> ids = new ArrayList();
	    List<Product> products = new ArrayList<Product>();
	    Cursor cursor = Cursor.newBuilder().build();
	    Results<ScoredDocument> result;
	    Set<String> hs = new HashSet();
	    do {
	        QueryOptions options = QueryOptions.newBuilder()
	            .setCursor(cursor)
	            .build();
	        Query query = Query.newBuilder().setOptions(options).build("");
	    
	        result = index.search(query);
	        System.out.println("------"+result.getNumberFound() + "---" + result.getNumberReturned());
	        integer.setValue(result.getNumberFound());
	        int numberRetrieved = result.getNumberReturned();
	        cursor = result.getCursor();

	        
	        if (numberRetrieved > 0) {
	        	for (ScoredDocument document : result) {
	        		String[] k = document.getOnlyField(ProductContract.COLUMN_KEYWORDS).getText().split(" ");
	        		for(String s : k){
	        			System.out.println("---"+s);
	        			hs.add(s);
	        		}
	    		}
	        }
	        
	        Keywords keywords = new Keywords();
	        keywords.setKeywords(hs.toArray(new String[hs.size()]));
	    
	        ofy().save().entity(keywords).now();
	        
	    }
	    while (cursor != null);
	    
	}
	
	@ApiMethod(name="fetchKeywordList", path="fetchKeywordList", httpMethod=HttpMethod.POST)
	public void fetchKeywordList(User user) throws UnauthorizedException{
		
		if(user == null){
    		throw new UnauthorizedException("Authoruzation required");
    	}
		
		Key<Admin> adminKey = Key.create(Admin.class, user.getEmail());
		Admin admin = ofy().load().key(adminKey).now();
		
		if(		 admin==null || 
				!admin.isActive()){
			throw new UnauthorizedException("Authentication fail");
		}
		
		Keywords keywords = new Keywords();
		keywords = ofy().load().entity(keywords).now();
		
		
		String s = "[";
		for(String keyword: keywords.getKeywords()){
			s += "\"" + keyword + "\", ";
		}
		s += "]";
		System.out.println(keywords.getKeywords().length+"---"+s);
	}
	
}
