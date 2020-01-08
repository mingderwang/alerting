package com.amazon.opendistroforelasticsearch.alerting.destination;

import org.elasticsearch.common.settings.SecureSetting;
import org.elasticsearch.common.settings.SecureString;
import org.elasticsearch.common.settings.Setting;
import org.elasticsearch.common.settings.Setting.Property;
/**
 * settings specific to [Notification from AlertingPlugin]. These settings include things like mail server settings
 */
class NotificationSettings {

    static final Setting<String> MAIL_HOST = Setting.simpleString("opendistro_alerting.destination.mail.host", "localhost", Property.NodeScope);
    static final Setting<Integer> MAIL_PORT = Setting.intString("opendistro_alerting.destination.mail.port", 25, Property.NodeScope);
    static final Setting<String> MAIL_METHOD = Setting.simpleString("opendistro_alerting.destination.mail.method", "none", Property.NodeScope);
    static final Setting<String> MAIL_FROM = Setting.simpleString("opendistro_alerting.destination.mail.frpm", "opendistro_alerting@localhost", Property.NodeScope);
    static final Setting<String> MAIL_USERNAME = Setting.simpleString("opendistro_alerting.destination.mail.username", "", Property.NodeScope);
    static final Setting<SecureString> MAIL_PASSWORD = SecureSetting.secureString("opendistro_alerting.destination.mail.password", "");

}