package org.vaadin.junit;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.vaadin.junit.helpers.MenuHelper;

import com.vaadin.flow.router.RouterLink;
import com.vaadin.karibu.KaribuTest;
import com.vaadin.karibu.locator.LoginFormLocator;

/**
 * Test logging in with different users through the login page.
 */
public class LoginTest extends KaribuTest {

    @Test
    public void loginUser_noAdminMenuItemAvailable() {
        $(LoginFormLocator.class).first().login("user", "user");

        // Get all RouterLinks with the css className "menu-link"
        final List<RouterLink> menuLinks = £(RouterLink.class).withClassName(
                "menu-link").all();

        final long admin = menuLinks.stream()
                .filter(link -> MenuHelper.getMenuItemText(link)
                        .equalsIgnoreCase("admin")).count();
        Assert.assertEquals(0, admin);
    }

    @Test
    public void loginAdmin_adminMenuItemAvailable() {
        $(LoginFormLocator.class).first().login("admin", "admin");

        final List<RouterLink> menuLinks = £(RouterLink.class).withClassName(
                "menu-link").all();

        final long admin = menuLinks.stream()
                .filter(link -> MenuHelper.getMenuItemText(link)
                        .equalsIgnoreCase("admin")).count();
        Assert.assertEquals(1, admin);
    }

}