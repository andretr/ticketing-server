SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

--
-- Name: ticket_status; Type: TYPE; Schema: public; Owner: ticketing
--

CREATE TYPE public.ticket_status AS ENUM (
    'CREATED',
    'ACKNOWLEDGED',
    'CLOSED',
    'OPEN',
    'SOLVED',
    'DELETED'
    );

ALTER TYPE public.ticket_status OWNER TO ticketing;

--
-- Name: ticket_status; Type: TYPE; Schema: public; Owner: ticketing
--

CREATE TYPE public.time_unit AS ENUM (
    'NANOSECONDS',
    'MICROSECONDS',
    'MILLISECONDS',
    'SECONDS',
    'MINUTES',
    'HOURS',
    'DAYS'
    );

ALTER TYPE public.time_unit OWNER TO ticketing;

-- public.tenants definition
CREATE TABLE public.tenants (
    tenant_id uuid NOT NULL,
    hidden_tenant boolean NOT NULL,
    name varchar(255) NOT NULL,
    tenant_code varchar(255) NOT NULL
);

ALTER TABLE public.tenants OWNER TO ticketing;

-- public.users definition
CREATE TABLE public.users (
    user_id uuid NOT NULL,
    tenant_id uuid NOT NULL,
    email varchar(255) NOT NULL,
    name varchar(255) NOT NULL
);

ALTER TABLE public.users OWNER TO ticketing;

-- public.categories definition
CREATE TABLE public.categories (
    category_id uuid NOT NULL,
    tenant_id uuid NOT NULL,
    default_priority_id uuid,
    category_name varchar(255) NOT NULL,
    created timestamp with time zone DEFAULT now() NOT NULL,
    modified timestamp with time zone DEFAULT now() NOT NULL
);

ALTER TABLE public.categories OWNER TO ticketing;

-- public.priorities definition
CREATE TABLE public.priorities (
    priority_id uuid NOT NULL,
    category_id uuid NOT NULL,
    priority_name varchar(255) NOT NULL,
    sla_overdue_threshold integer NOT NULL,
    sla_overdue_unit public.time_unit NOT NULL,
    sla_warning_threshold integer NOT NULL,
    sla_warning_unit public.time_unit NOT NULL,
    created timestamp with time zone DEFAULT now() NOT NULL,
    modified timestamp with time zone DEFAULT now() NOT NULL
);

ALTER TABLE public.priorities OWNER TO ticketing;

-- public.tickets definition
CREATE TABLE public.tickets (
    ticket_id uuid NOT NULL,
    category_id uuid NOT NULL,
    priority_id uuid NOT NULL,
    created_by varchar(255) NOT NULL,
    title varchar(255) NOT NULL,
    status public.ticket_status NOT NULL,
    description text NOT NULL,
    event_data text NOT NULL,
    acknowledged timestamp with time zone DEFAULT now(),
    solved timestamp with time zone DEFAULT now(),
    closed timestamp with time zone DEFAULT now(),
    created timestamp with time zone DEFAULT now() NOT NULL,
    modified timestamp with time zone DEFAULT now() NOT NULL
);

ALTER TABLE public.tickets OWNER TO ticketing;

-- public.ticket_comments definition
CREATE TABLE public.ticket_comments (
    ticket_comments_id uuid NOT NULL,
    ticket_id uuid NOT NULL,
    comment text NOT NULL,
    created timestamp with time zone DEFAULT now() NOT NULL,
    modified timestamp with time zone DEFAULT now() NOT NULL
);

ALTER TABLE public.ticket_comments OWNER TO ticketing;

-- public.ticket_history definition
CREATE TABLE public.ticket_history (
    ticket_history_id uuid NOT NULL,
    ticket_id uuid NOT NULL,
    event_detail text NOT NULL,
    created timestamp with time zone DEFAULT now() NOT NULL
);

ALTER TABLE public.ticket_history OWNER TO ticketing;

-- public.ticket_recipients definition
CREATE TABLE public.ticket_recipients (
    ticket_id uuid NOT NULL,
    user_id uuid NOT NULL
);

ALTER TABLE public.ticket_recipients OWNER TO ticketing;

-- public.user_categories definition
CREATE TABLE public.user_categories (
    category_id uuid NOT NULL,
    user_id uuid NOT NULL
);

ALTER TABLE public.user_categories OWNER TO ticketing;

-- TENANTS ALTERATIONS
ALTER TABLE ONLY public.tenants ADD CONSTRAINT tenants_pkey UNIQUE (tenant_id);
ALTER TABLE ONLY public.tenants
    ADD CONSTRAINT tenants_tenant_code_key UNIQUE (tenant_code);

-- USERS ALTERATIONS
ALTER TABLE ONLY public.users ADD CONSTRAINT users_pkey UNIQUE (user_id);
ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_tenant_id_fkey FOREIGN KEY (tenant_id) REFERENCES public.tenants(tenant_id) ON DELETE CASCADE;

-- CATEGORIES ALTERATIONS
ALTER TABLE ONLY public.categories ADD CONSTRAINT categories_pkey UNIQUE (category_id);
ALTER TABLE ONLY public.categories
    ADD CONSTRAINT categories_tenant_id_fkey FOREIGN KEY (tenant_id) REFERENCES public.tenants(tenant_id) ON DELETE CASCADE;
ALTER TABLE ONLY public.categories
    ADD CONSTRAINT providers_category_name_key UNIQUE (category_name);

-- PRIORITIES ALTERATIONS
ALTER TABLE ONLY public.priorities ADD CONSTRAINT priorities_pkey UNIQUE (priority_id);
ALTER TABLE ONLY public.priorities
    ADD CONSTRAINT priorities_category_id_fkey FOREIGN KEY (category_id) REFERENCES public.categories(category_id) ON DELETE CASCADE;

--Additional constraint for default_priority_id in public.category
ALTER TABLE ONLY public.categories
    ADD CONSTRAINT categories_default_priority_id_fkey FOREIGN KEY (default_priority_id) REFERENCES public.priorities(priority_id) ON DELETE CASCADE;

-- TICKETS ALTERATIONS
ALTER TABLE ONLY public.tickets ADD CONSTRAINT tickets_pkey UNIQUE (ticket_id);
ALTER TABLE ONLY public.tickets
    ADD CONSTRAINT tickets_category_id_fkey FOREIGN KEY (category_id) REFERENCES public.categories(category_id) ON DELETE CASCADE;
ALTER TABLE ONLY public.tickets
    ADD CONSTRAINT tickets_priority_id_fkey FOREIGN KEY (priority_id) REFERENCES public.priorities(priority_id) ON DELETE CASCADE;

-- TICKET_COMMENTS ALTERATIONS
ALTER TABLE ONLY public.ticket_comments ADD CONSTRAINT ticket_comments_pkey UNIQUE (ticket_comments_id);
ALTER TABLE ONLY public.ticket_comments
    ADD CONSTRAINT ticket_comments_ticket_id_fkey FOREIGN KEY (ticket_id) REFERENCES public.tickets(ticket_id) ON DELETE CASCADE;

-- TICKET_HISTORY ALTERATIONS
ALTER TABLE ONLY public.ticket_history ADD CONSTRAINT ticket_history_pkey UNIQUE (ticket_history_id);
ALTER TABLE ONLY public.ticket_history
    ADD CONSTRAINT ticket_history_ticket_id_fkey FOREIGN KEY (ticket_id) REFERENCES public.tickets(ticket_id) ON DELETE CASCADE;

-- TICKET_RECIPIENTS ALTERATIONS
ALTER TABLE ONLY public.ticket_recipients ADD CONSTRAINT ticket_recipients_pkey UNIQUE (ticket_id, user_id);
ALTER TABLE ONLY public.ticket_recipients
    ADD CONSTRAINT ticket_recipients_ticket_id_fkey FOREIGN KEY (ticket_id) REFERENCES public.tickets(ticket_id) ON DELETE CASCADE;
ALTER TABLE ONLY public.ticket_recipients
    ADD CONSTRAINT ticket_recipients_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(user_id) ON DELETE CASCADE;

-- USER_CATEGORIES ALTERATIONS
ALTER TABLE ONLY public.user_categories
    ADD CONSTRAINT user_categories_pkey UNIQUE (user_id, category_id);
ALTER TABLE ONLY public.user_categories
    ADD CONSTRAINT user_categories_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(user_id) ON DELETE CASCADE;
ALTER TABLE ONLY public.user_categories
    ADD CONSTRAINT user_categories_category_id_fkey FOREIGN KEY (category_id) REFERENCES public.categories(category_id) ON DELETE CASCADE;

