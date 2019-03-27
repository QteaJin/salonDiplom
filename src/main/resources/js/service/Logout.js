/**
 * 
 */
function logoutSession() {

	var date = new Date(0);
	document.cookie = "token=; path=/; expires=" + date.toUTCString();
	createLoginLogoutButton();
	location.href = '/';
	
}