package by.bsuir.ief.system.fabric.model.repository.mysql;

import java.sql.SQLException;

public class ConnectionDataBaseCloseException extends SQLException {

    /**
     * Constructs a <code>SQLException</code> object with a given
     * <code>reason</code>. The  <code>SQLState</code>  is initialized to
     * <code>null</code> and the vendor code is initialized to 0.
     * <p>
     * The <code>cause</code> is not initialized, and may subsequently be
     * initialized by a call to the
     * {@link Throwable#initCause(Throwable)} method.
     * <p>
     *
     * @param reason a description of the exception
     */
    public ConnectionDataBaseCloseException(String reason) {
        super(reason);
    }

    /**
     * Constructs a <code>SQLException</code> object with a given
     * <code>reason</code> and  <code>cause</code>.
     * The <code>SQLState</code> is  initialized to <code>null</code>
     * and the vendor code is initialized to 0.
     * <p>
     *
     * @param reason a description of the exception.
     * @param cause  the underlying reason for this <code>SQLException</code>
     *               (which is saved for later retrieval by the <code>getCause()</code> method);
     *               may be null indicating the cause is non-existent or unknown.
     * @since 1.6
     */
    public ConnectionDataBaseCloseException(String reason, Throwable cause) {
        super(reason, cause);
    }
}
