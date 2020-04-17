SELECT tov.MNTOV AS MnTov, NAT, NEI,
       (SELECT SUM(KVP) FROM AO_RS2 rs2 WHERE tov.MNTOV = rs2.MNTOV (+) ) AS Qt,
       (SELECT SUM(KVP * ZEN) FROM AO_RS2 rs2 WHERE tov.MNTOV = rs2.MNTOV (+) ) AS SumTov
FROM AO_TOV tov
ORDER BY Nat;

SELECT * FROM
    (
        SELECT a.*, rownum r__
        FROM
            (
                SELECT tov.MNTOV AS MnTov, NAT, NEI,
                       (SELECT SUM(KVP) FROM AO_RS2 rs2 WHERE tov.MNTOV = rs2.MNTOV (+) ) AS Qt,
                       (SELECT SUM(KVP * ZEN) FROM AO_RS2 rs2 WHERE tov.MNTOV = rs2.MNTOV (+) ) AS SumTov
                FROM AO_TOV tov
                ORDER BY Nat
            ) a
        WHERE rownum < ((2 * 5) + 1 )
    )
WHERE r__ >= (((2-1) * 5) + 1);
// 2 - номер страницы
// 5 - сколько на странице