/*
 *   Copyright 2019 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License").
 *   You may not use this file except in compliance with the License.
 *   A copy of the License is located at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   or in the "license" file accompanying this file. This file is distributed
 *   on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 *   express or implied. See the License for the specific language governing
 *   permissions and limitations under the License.
 */

package com.amazon.opendistroforelasticsearch.alerting.destination.message;

import org.elasticsearch.common.Strings;

import java.util.Map;

/**
 * This class holds the content of a Mail message
 */
public class MailMessage extends BaseMessage {

    private String message;
    private String recipients;
    private String subject;

    private MailMessage(final DestinationType destinationType,
                                 final String destinationName,
                                 final String recipients,
                                 final String subject,
                                 final String message) {

        super(destinationType, destinationName, message);

        if (DestinationType.MAIL != destinationType) {
            throw new IllegalArgumentException("Channel Type does not match Mail");
        }

        if (Strings.isNullOrEmpty(message)) {
            throw new IllegalArgumentException("Message content is missing");
        }

        if(Strings.isNullOrEmpty(recipients)) {
            throw new IllegalArgumentException("Comma separated recipients should be provided");
        }

        this.message = message;
        this.recipients = recipients;
        this.subject = subject == "" ? destinationName : subject;
    }

    @Override
    public String toString() {
        return "DestinationType: " + destinationType + ", DestinationName:" +  destinationName +
                ", Message: " + message;
    }

    public static class Builder {
        private String message;
        private DestinationType destinationType;
        private String destinationName;
        private String recipients;
        private String subject;

        public Builder(String destinationName) {
            this.destinationName = destinationName;
            this.destinationType = DestinationType.MAIL;
        }

        public MailMessage.Builder withRecipients(String recipients) {
            this.recipients = recipients;
            return this;
        }

        public MailMessage.Builder withSubject(String subject) {
            this.subject = subject;
            return this;
        }

        public MailMessage.Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public MailMessage build() {
            MailMessage mailMessage = new MailMessage(
                    this.destinationType, this.destinationName,
                    this.recipients, this.subject, this.message);
            return mailMessage;
        }
    }

    public String getRecipients() {
        return recipients;
    }

    public String getSubject() {
        return subject;
    }

}