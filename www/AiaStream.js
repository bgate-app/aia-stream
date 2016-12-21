var exec = require('cordova/exec');

var AiaStream = function () {

}

AiaStream.prototype.startStream = function (options) {
	options = options || {};
	exec(options.successCallback || null, options.errorCallback || null, "AiaStream", "startStream", [url, options]);
};

AiaStream.prototype.stopStream = function (options) {
	options = options || {};
	exec(options.successCallback || null, options.errorCallback || null, "AiaStream", "stopStream", [options]);
};

var aiaStream = new AiaStream();

if (!window.plugins) {
	window.plugins = {};
}
window.plugins.aiaStream = aiaStream;

module.exports = aiaStream;
