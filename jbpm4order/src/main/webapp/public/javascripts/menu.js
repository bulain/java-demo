// Cookie handling functions

function saveToConglomerateCookie(cookieName, name, value)
{
    var cookieValue = getCookieValue(cookieName);
    cookieValue = addOrAppendToValue(name, value, cookieValue);

    saveCookie(cookieName, cookieValue, 365);
}

function readFromConglomerateCookie(cookieName, name, defaultValue)
{
    var cookieValue = getCookieValue(cookieName);
    var value = getValueFromCongolmerate(name, cookieValue);
    if(value != null)
    {
        return value;
    }

    return defaultValue;
}

function eraseFromConglomerateCookie(cookieName, name)
{
    saveToConglomerateCookie(cookieName, name,"");
}

function getValueFromCongolmerate(name, cookieValue)
{
    var newCookieValue = null;
    // a null cookieValue is just the first time through so create it
    if(cookieValue == null)
    {
        cookieValue = "";
    }
    var eq = name + "=";
    var cookieParts = cookieValue.split('|');
    for(var i = 0; i < cookieParts.length; i++) {
        var cp = cookieParts[i];
        while (cp.charAt(0)==' ') {
            cp = cp.substring(1,cp.length);
        }
        // rebuild the value string exluding the named portion passed in
        if (cp.indexOf(name) == 0) {
            return cp.substring(eq.length, cp.length);
        }
    }
    return null;
}

//either append or replace the value in the cookie string
function addOrAppendToValue(name, value, cookieValue)
{
    var newCookieValue = "";
    // a null cookieValue is just the first time through so create it
    if(cookieValue == null)
    {
        cookieValue = "";
    }

    var cookieParts = cookieValue.split('|');
    for(var i = 0; i < cookieParts.length; i++) {
        var cp = cookieParts[i];

        // ignore any empty tokens
        if(cp != "")
        {
            while (cp.charAt(0)==' ') {
                cp = cp.substring(1,cp.length);
            }
            // rebuild the value string exluding the named portion passed in
            if (cp.indexOf(name) != 0) {
                newCookieValue += cp + "|";
            }
        }
    }

    // always append the value passed in if it is not null or empty
    if(value != null && value != '')
    {
        var pair = name + "=" + value;
        if((newCookieValue.length + pair.length) < 4020)
        {
            newCookieValue += pair;
        }
    }
    return newCookieValue;
}

function getCookieValue(name)
{
    var eq = name + "=";
    var ca = document.cookie.split(';');
    for(var i=0;i<ca.length;i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') {
            c = c.substring(1,c.length);
        }
        if (c.indexOf(eq) == 0) {
            return c.substring(eq.length,c.length);
        }
    }

    return null;
}

function saveCookie(name,value,days)
{
  var ex;
  if (days) {
    var d = new Date();
    d.setTime(d.getTime()+(days*24*60*60*1000));
    ex = ";expires="+d.toGMTString();
  }
  else {
    ex = "";
  }
  document.cookie = name + "=" + value + ex + ";path=/";
}

/*
Reads a cookie. If none exists, then it returns and 
*/
function readCookie(name, defaultValue)
{
  var cookieVal = getCookieValue(name);
  if(cookieVal != null)
  {
      return cookieVal;
  }

  // No cookie found, then save a new one as on!
  if (defaultValue)
  {
      saveCookie(name, defaultValue, 365);
      return defaultValue;
  }
  else
  {
      return null;
  }
}

function eraseCookie(name)
{
  saveCookie(name,"",-1);
}


/*
	Toggles the admin menu on & off (using cookies)
*/

var areAllMenusOpen = true;

function openMenu(sSectionID) 
{
	var eHeader = document.getElementById(sSectionID);
	var eSection = document.getElementById(sSectionID + "_body");

    eHeader.className = "headerOpen";
    eSection.style.display = "";
    eraseFromConglomerateCookie("_cookie", sSectionID);
}

function closeMenu(sSectionID)
{
	var eHeader = document.getElementById(sSectionID);
	var eSection = document.getElementById(sSectionID + "_body");

    eHeader.className = "headerClosed";
    eSection.style.display = "none";
    saveToConglomerateCookie("_cookie", sSectionID, '0');

    areAllMenusOpen = false;
}

function toggleMenu(sSectionID)
{
	if (readFromConglomerateCookie("_cookie", sSectionID, '1') == "1")
	{
        closeMenu(sSectionID);
        restoreShowHideAllMenu();
	}
	else
	{
        openMenu(sSectionID);
	}
}

/*
	Restores a state of a menu
*/

function restoreMenu(sSectionID)
{
	if (readFromConglomerateCookie("_cookie", sSectionID, '1') == "1")
	{
        openMenu(sSectionID);
	}
    else
    {
        closeMenu(sSectionID);
    }
}

function restoreShowHideAllMenu()
{
    if (areAllMenusOpen)
    {
        var eHideAll = document.getElementById("hideAllMenu");
        eHideAll.style.display = "";

        var eShowAll = document.getElementById("showAllMenu");
        eShowAll.style.display = "none";
    }
    else
    {
        var eHideAll = document.getElementById("hideAllMenu");
        eHideAll.style.display = "none";

        var eShowAll = document.getElementById("showAllMenu");
        eShowAll.style.display = "";
    }
}

// hidden or show all menu
function hideAll(){
    for (i=0 ; i < allMenus.length ; i++){
        if(document.getElementById(allMenus[i])!=null ){
            closeMenu(allMenus[i]);
        }
    }

    areAllMenusOpen = false;
    restoreShowHideAllMenu();
}

function showAll(){
    for (i=0 ; i < allMenus.length ; i++){
        if(document.getElementById(allMenus[i])!=null ){
            openMenu(allMenus[i]);
        }
    }

    areAllMenusOpen = true;
    restoreShowHideAllMenu();
}