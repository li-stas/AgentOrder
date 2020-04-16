SELECT s_tag.kod AS "Код ТА", s_tag.FIO AS "ФИО ТА",
    (SELECT SUM(rs2.KVP * rs2.ZEN) AS SumOrd FROM  AO_RS1 rs1, AO_RS2 rs2 WHERE s_tag.kod = rs1.KTA and rs1.TTN = rs2.TTN) AS "Сумма",
    (SELECT COUNT(TTN) FROM AO_RS1 rs1 WHERE s_tag.kod = rs1.KTA) AS "К-во заказов"
FROM AO_S_TAG s_tag
WHERE KTAS = 31 and KTAS <> Kod;