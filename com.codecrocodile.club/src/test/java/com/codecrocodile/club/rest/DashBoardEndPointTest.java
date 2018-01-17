package com.codecrocodile.club.rest;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.codecrocodile.club.model.Post;
import com.codecrocodile.club.security.MemberDetails;
import com.codecrocodile.club.service.DashBoardService;
import com.codecrocodile.club.testcategories.SlowIntegrationTest;

@Category(SlowIntegrationTest.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/root-context.xml")
public class DashBoardEndPointTest extends DBTestCase {

	@Autowired
	private DashBoardEndPoint dashBoardEndPoint;
	
	public DashBoardEndPointTest() {
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "com.mysql.jdbc.Driver" );
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:mysql://localhost:3306/clubmanager" );
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "root" );
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "" );
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("datasets/FlatDatabase.xml").getFile());
		
		return new FlatXmlDataSetBuilder().build(new FileInputStream(file));
	}

    protected DatabaseOperation getSetUpOperation() throws Exception {
        return DatabaseOperation.CLEAN_INSERT;
    }

    protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.NONE;
    }

	@Test
	public void testSavePost() {
		this.setUpSecurityContext();
		
		Post post = new Post();
		post.setText("This is some saved text that is safe.");
		
		Post savedPost = dashBoardEndPoint.putPost(post);
		assertTrue("Expected that generated primary key would have been set", savedPost.getPostId() > 0);
	}
	
	@Test
	public void testGetPosts() {
//		this.setUpSecurityContext();
//		
//		List<Post> posts = dashBoardEndPoint.getPosts(1, 0, 10);
//		
//		System.out.println("number of posts = " + posts.size());
//		
//		assertTrue("Expected not to exceed standard page size", posts.size() <= DashBoardService.pageSize);
	}
	
	private void setUpSecurityContext() {
		MemberDetails memberDetails = new MemberDetails("Test User", "Test Password", AuthorityUtils.createAuthorityList("ROLE_AUTH", "ROLE_USER"));
		memberDetails.setUserId(1);
		memberDetails.setAccountId(1);
		
        List<GrantedAuthority> grantedAuths = new ArrayList<>();
        grantedAuths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
        Authentication auth = new UsernamePasswordAuthenticationToken(memberDetails, "Test Password", grantedAuths);
        SecurityContextHolder.getContext().setAuthentication(auth);
	}

}
