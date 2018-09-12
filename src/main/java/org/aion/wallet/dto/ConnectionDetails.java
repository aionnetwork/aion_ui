package org.aion.wallet.dto;

import java.util.Objects;

public class ConnectionDetails {

    private final String id;
    private final String name;
    private final String protocol;
    private final String address;
    private final String port;

    public ConnectionDetails(final String id, final String name, final String protocol, final String address, final String port) {
        this.id = id;
        if (name != null && !name.isEmpty()) {
            this.name = name;
        } else {
            this.name = "Unnamed connection";
        }
        this.protocol = protocol;
        this.address = address;
        this.port = port;
    }

    public ConnectionDetails(final String connection) {
        try {
            final String[] split = connection.split(":");
            id = split[0];
            name = split[1];
            protocol = split[2];
            address = split[3].substring(2);
            port = split[4];
        } catch (final Exception e) {
            throw new IllegalArgumentException("Invalid connection string: " + connection, e);
        }
    }

    public String getAddress() {
        return address;
    }

    public String getPort() {
        return port;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConnectionDetails that = (ConnectionDetails) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return name;
    }

    public final String serialized() {
        return id + ":" + name + ":" + toConnectionString();
    }

    public String toConnectionString() {
        return protocol + "://" + address + ":" + port;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getId() {
        return id;
    }
}
