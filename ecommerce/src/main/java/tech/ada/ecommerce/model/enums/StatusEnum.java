package tech.ada.ecommerce.model.enums;

public enum StatusEnum {

    EM_ABERTO("em aberto"),
    PGTO_APROVADO("pagamento aprovado"),
    PGTO_PROCESSAMENTO("pagamento em processamento"),
    PGTO_REJEITADO("pagamento rejeitado"),
    CANCELADA("compra cacelada"),
    EM_TRANSPORTE("em transporte"),
    FINALIZADO("finalizado");

    private final String status;

    StatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
