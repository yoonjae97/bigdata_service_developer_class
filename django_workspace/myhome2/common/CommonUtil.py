def dictfetchall(cursor):
    columns = [ col[0].lower() for col in cursor.description]
    # cursor의 description의 각 필드 이름 정보 - 배열
    # columns <- ['id', 'title', 'contents', 'writer']
    return [dict(zip(columns, row)) for row in cursor.fetchall()]